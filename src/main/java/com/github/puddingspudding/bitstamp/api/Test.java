package com.github.puddingspudding.bitstamp.api;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {


    public static double STRAT_TOTAL_PCT = .75;
    public static double STRAT_STEPS_PCT = .05;
    public static double STRAT_SELL_PCT = 0.05;
    public static double MIN_ODER_VALUE = 25.0;

    public static void main(String[] args) throws Exception {
        String apiKey = System.getProperty("apiKey");
        String apiSecretKey = System.getProperty("apiSecretKey");

        STRAT_TOTAL_PCT = Integer.parseInt(System.getProperty("totalPct")) / 100.0;
        STRAT_STEPS_PCT = Integer.parseInt(System.getProperty("stepsPct")) / 100.0;
        STRAT_SELL_PCT = Integer.parseInt(System.getProperty("sellPct")) / 100.0;

        CurrencyPair currencyPair = CurrencyPair.valueOf(System.getProperty("pair"));

        int maxFiatToSpend = Integer.parseInt(System.getProperty("maxFiat"));

        Http api = new Http(apiKey, apiSecretKey);
        Ticker ticker = api.getTicker(currencyPair).join();
        CurrencyPairInfo currencyPairInfo = api.getCurrencyPairInfo().join()
            .stream()
            .peek(x -> System.out.println(x))
            .filter(pair -> pair.getSymbol().equals(currencyPair.toName()))
            .findFirst()
            .orElseThrow();

        System.out.println(ticker);
        System.out.println(currencyPairInfo);

        List<Order> orders = api.getOpenOrders(currencyPair).join()
            .stream()
            .filter(order -> order.getType() == 0)
            .collect(Collectors.toList());

        Order highestOpenOrder = orders.stream()
            .max(Comparator.comparing(Order::getPrice))
            .orElse(null);

        List<Transaction> transactions = api.getTransactions().join();

        System.out.println(transactions);

        Transaction latestTrade = transactions.stream()
            .filter(transaction -> transaction.getType() == Transaction.Type.TRADE.ordinal())
            .filter(transaction -> {
                try {
                    LocalDateTime.parse(transaction.getDatetime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n"));
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            })
            .max(Comparator.comparing(transaction -> LocalDateTime.parse(transaction.getDatetime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.n"))))
            .orElse(null);

        System.out.printf("Latest Trade: %s\n", latestTrade);
        System.out.printf("Highest Open Order: %s\n", highestOpenOrder);

        if (highestOpenOrder != null && highestOpenOrder.getPrice() > (ticker.getLast() - (ticker.getLast() * STRAT_STEPS_PCT))) {
            System.exit(0);
        }
        for (Order order : orders) {
            System.out.print(orders);
            api.cancelOrder(order);
        }

        Balance balance = api.getBalance().join();
        double eurAvailable = Math.min(maxFiatToSpend, balance.getEur_available());
        double eurPerTrade = Math.max(MIN_ODER_VALUE, eurAvailable / (STRAT_TOTAL_PCT / STRAT_STEPS_PCT));

        double last = ticker.getLast();

        for (double x = STRAT_STEPS_PCT; x <= STRAT_TOTAL_PCT; x += STRAT_STEPS_PCT) {
            double buyPrice = last - (last * x);
            double amount = eurPerTrade / buyPrice;
            double sellLimit = buyPrice + (buyPrice * STRAT_SELL_PCT);
            System.out.printf("%f * %f = %f€ => %f\n", amount, buyPrice, amount*buyPrice, eurPerTrade + (eurPerTrade * STRAT_SELL_PCT));

            Order buyOrder = new Order();
            buyOrder.setAmount(amount);
            buyOrder.setPrice(buyPrice);
            buyOrder.setCurrency_pair(currencyPair.toName());

            System.out.println(buyOrder);
            System.out.println(sellLimit);
            CompletableFuture<Order> buy = api.buy(
                buyOrder,
                sellLimit,
                currencyPairInfo.getBaseDecimal(),
                currencyPairInfo.getCounterDecimals()
            );

            System.out.println(buy.join());
        }

        System.out.println("end");

        /*for (Transaction transaction : transactions) {
            System.out.println(
                gson.toJson(
                    transaction
                )
            );
        }*/

    }

}
