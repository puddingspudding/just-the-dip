package com.github.puddingspudding.bitstamp.api;

import com.google.gson.Gson;

public class Order {

    public static double MIN_VALUE = 10.0;

    private long id;
    private String datetime;
    private int type;
    private double price;
    private double amount;
    private String currency_pair;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCurrency_pair() {
        return currency_pair;
    }

    public void setCurrency_pair(String currency_pair) {
        this.currency_pair = currency_pair;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
