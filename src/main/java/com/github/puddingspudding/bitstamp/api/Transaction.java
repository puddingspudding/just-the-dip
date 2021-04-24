package com.github.puddingspudding.bitstamp.api;

import com.google.gson.Gson;

public class Transaction {

    private double fee;
    private long order_id;
    private String datetime;
    private double usd;
    private double btc;
    private double eth;
    private double eth_eur;
    private int type;
    private long id;
    private double eur;

    public enum Type {
        DEPOSIT,
        WITHDRAW,
        TRADE,
        SUB_ACCOUNT_TRANSFER
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getUsd() {
        return usd;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }

    public double getBtc() {
        return btc;
    }

    public void setBtc(double btc) {
        this.btc = btc;
    }

    public double getEth() {
        return eth;
    }

    public void setEth(double eth) {
        this.eth = eth;
    }

    public double getEth_eur() {
        return eth_eur;
    }

    public void setEth_eur(double eth_eur) {
        this.eth_eur = eth_eur;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getEur() {
        return eur;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
