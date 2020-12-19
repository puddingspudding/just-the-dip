package com.github.puddingspudding.bitstamp.api;

import com.google.gson.Gson;

public class Balance {

    private double btc_balance;
    private double eur_balance;
    private double xrp_balance;
    private double eth_balance;
    private double xlm_balance;

    private double btc_reserved;
    private double eur_reserved;
    private double xrp_reserved;
    private double eth_reserved;
    private double xlm_reserved;

    private double btc_available;
    private double eur_available;
    private double xrp_available;
    private double eth_available;
    private double xlm_available;

    private double xrpeur_fee;
    private double btceur_fee;
    private double xlmeur_fee;

    private double fee;

    public double getBtc_balance() {
        return btc_balance;
    }

    public void setBtc_balance(double btc_balance) {
        this.btc_balance = btc_balance;
    }

    public double getEur_balance() {
        return eur_balance;
    }

    public void setEur_balance(double eur_balance) {
        this.eur_balance = eur_balance;
    }

    public double getXrp_balance() {
        return xrp_balance;
    }

    public void setXrp_balance(double xrp_balance) {
        this.xrp_balance = xrp_balance;
    }

    public double getEth_balance() {
        return eth_balance;
    }

    public void setEth_balance(double eth_balance) {
        this.eth_balance = eth_balance;
    }

    public double getXlm_balance() {
        return xlm_balance;
    }

    public void setXlm_balance(double xlm_balance) {
        this.xlm_balance = xlm_balance;
    }

    public double getBtc_reserved() {
        return btc_reserved;
    }

    public void setBtc_reserved(double btc_reserved) {
        this.btc_reserved = btc_reserved;
    }

    public double getEur_reserved() {
        return eur_reserved;
    }

    public void setEur_reserved(double eur_reserved) {
        this.eur_reserved = eur_reserved;
    }

    public double getXrp_reserved() {
        return xrp_reserved;
    }

    public void setXrp_reserved(double xrp_reserved) {
        this.xrp_reserved = xrp_reserved;
    }

    public double getEth_reserved() {
        return eth_reserved;
    }

    public void setEth_reserved(double eth_reserved) {
        this.eth_reserved = eth_reserved;
    }

    public double getXlm_reserved() {
        return xlm_reserved;
    }

    public void setXlm_reserved(double xlm_reserved) {
        this.xlm_reserved = xlm_reserved;
    }

    public double getBtc_available() {
        return btc_available;
    }

    public void setBtc_available(double btc_available) {
        this.btc_available = btc_available;
    }

    public double getEur_available() {
        return eur_available;
    }

    public void setEur_available(double eur_available) {
        this.eur_available = eur_available;
    }

    public double getXrp_available() {
        return xrp_available;
    }

    public void setXrp_available(double xrp_available) {
        this.xrp_available = xrp_available;
    }

    public double getEth_available() {
        return eth_available;
    }

    public void setEth_available(double eth_available) {
        this.eth_available = eth_available;
    }

    public double getXlm_available() {
        return xlm_available;
    }

    public void setXlm_available(double xlm_available) {
        this.xlm_available = xlm_available;
    }

    public double getXrpeur_fee() {
        return xrpeur_fee;
    }

    public void setXrpeur_fee(double xrpeur_fee) {
        this.xrpeur_fee = xrpeur_fee;
    }

    public double getBtceur_fee() {
        return btceur_fee;
    }

    public void setBtceur_fee(double btceur_fee) {
        this.btceur_fee = btceur_fee;
    }

    public double getXlmeur_fee() {
        return xlmeur_fee;
    }

    public void setXlmeur_fee(double xlmeur_fee) {
        this.xlmeur_fee = xlmeur_fee;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
