package com.github.puddingspudding.justthedip;

import com.github.puddingspudding.bitstamp.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class Bot {


    public static double STRAT_TOTAL_PCT = .75;
    public static double STRAT_STEPS_PCT = .05;
    public static double STRAT_SELL_PCT = 0.05;

    public static void main(String[] args) throws Exception {
        String apiKey = System.getProperty("apiKey");
        if (apiKey == null || apiKey.isEmpty()) {
            throw new IllegalArgumentException("apiKey has to be provided");
        }

        String apiSecretKey = System.getProperty("apiSecretKey");
        if (apiSecretKey == null || apiSecretKey.isEmpty()) {
            throw new IllegalArgumentException("apiSecretKey has to be provided");
        }

        String totalPct = System.getProperty("totalPct");
        if (totalPct == null || totalPct.isEmpty()) {
            throw new IllegalArgumentException("totalPct has to be provided");
        }
        STRAT_TOTAL_PCT = Integer.parseInt(totalPct) / 100.0;

        String stepsPct = System.getProperty("stepsPct");
        if (stepsPct == null || stepsPct.isEmpty()) {
            throw new IllegalArgumentException("stepsPct has to be provided");
        }
        STRAT_STEPS_PCT = Integer.parseInt(stepsPct) / 100.0;

        String sellPct = System.getProperty("sellPct");
        if (sellPct == null || sellPct.isEmpty()) {
            throw new IllegalArgumentException("sellPct has to be provided");
        }
        STRAT_SELL_PCT = Integer.parseInt(sellPct) / 100.0;

        String pair = System.getProperty("pair");
        if (pair == null || pair.isEmpty()) {
            throw new IllegalArgumentException("pair has to be provided");
        }
        CurrencyPair currencyPair = CurrencyPair.valueOf(pair);

        String maxFiat = System.getProperty("maxFiat");
        if (maxFiat == null || maxFiat.isEmpty()) {
            throw new IllegalArgumentException("maxFiat has to be provided");
        }
        int maxFiatToSpend = Integer.parseInt(maxFiat);

        Http api = new Http(apiKey, apiSecretKey);
        Ticker ticker = api.getTicker(currencyPair).join();

        System.out.printf("CurrencyPairInfo:\n");
        CurrencyPairInfo currencyPairInfo = api.getCurrencyPairInfo().join()
            .stream()
            .peek(x -> System.out.println(x))
            .filter(item -> item.getSymbol().equals(currencyPair.toName()))
            .findFirst()
            .orElseThrow();

        System.out.printf("Ticker: %s\n", ticker);
        System.out.printf("CurrencyPairInfo: %s\n", currencyPairInfo);

        List<Order> orders = api.getOpenOrders(currencyPair).join()
            .stream()
            .filter(order -> order.getType() == 0)
            .collect(Collectors.toList());

        Order highestOpenOrder = orders.stream()
            .max(Comparator.comparing(Order::getPrice))
            .orElse(null);

        List<Transaction> transactions = api.getTransactions().join();

        System.out.printf("Transactions: %s\n", transactions);

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

        System.out.printf("Orders:\n");
        for (Order order : orders) {
            System.out.print(orders);
            api.cancelOrder(order);
        }

        Balance balance = api.getBalance().join();
        double eurAvailable = Math.min(maxFiatToSpend, balance.getEur_available());
        double eurPerTrade = Math.max(Order.MIN_VALUE, eurAvailable / (STRAT_TOTAL_PCT / STRAT_STEPS_PCT));

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

            System.out.printf("BuyOrder: %s\n", buyOrder);
            System.out.printf("SellLimit: %s\n", sellLimit);
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
