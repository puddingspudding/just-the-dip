package com.github.puddingspudding.bitstamp.api;

import com.google.gson.Gson;

// {"base_decimals": 8, "minimum_order": "25.0 USD", "name": "BTC/USD", "counter_decimals": 2, "trading": "Enabled", "url_symbol": "btcusd", "description": "Bitcoin / U.S. dollar"}
public class CurrencyPairInfo {
    private int base_decimals;
    private String name;
    private int counter_decimals;
    private String url_symbol;

    public int getBaseDecimal() {
        return base_decimals;
    }

    public void setBaseDecimal(int baseDecimal) {
        this.base_decimals = baseDecimal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCounterDecimals() {
        return counter_decimals;
    }

    public void setCounterDecimals(int counterDecimals) {
        this.counter_decimals = counterDecimals;
    }

    public String getSymbol() {
        return url_symbol;
    }

    public void setSymbol(String url_symbol) {
        this.url_symbol = url_symbol;
    }


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
