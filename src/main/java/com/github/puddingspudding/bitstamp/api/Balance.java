package com.github.puddingspudding.bitstamp.api;

import com.google.gson.Gson;

public class Balance {

    private double btc_balance;
    private double eur_balance;
    private double xrp_balance;
    private double eth_balance;
    private double xlm_balance;
    private double ltc_balance;
    private double link_balance;
    private double aave_balance;
    private double uma_balance;
    private double bat_balance;

    private double btc_reserved;
    private double eur_reserved;
    private double xrp_reserved;
    private double eth_reserved;
    private double xlm_reserved;
    private double ltc_reserved;
    private double link_reserved;
    private double aave_reserved;
    private double uma_reserved;
    private double bat_reserved;

    private double btc_available;
    private double eur_available;
    private double xrp_available;
    private double eth_available;
    private double xlm_available;
    private double ltc_available;
    private double link_available;
    private double aave_available;
    private double uma_available;
    private double bat_available;

    private double btceur_fee;
    private double xrpeur_fee;
    private double etheur_fee;
    private double xlmeur_fee;
    private double ltceur_fee;
    private double linkeur_fee;
    private double aaveeur_fee;
    private double umaeur_fee;
    private double bateur_fee;

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

    public double getLtc_balance() {
        return ltc_balance;
    }

    public void setLtc_balance(double ltc_balance) {
        this.ltc_balance = ltc_balance;
    }

    public double getLink_balance() {
        return link_balance;
    }

    public void setLink_balance(double link_balance) {
        this.link_balance = link_balance;
    }

    public double getAave_balance() {
        return aave_balance;
    }

    public void setAave_balance(double aave_balance) {
        this.aave_balance = aave_balance;
    }

    public double getUma_balance() {
        return uma_balance;
    }

    public void setUma_balance(double uma_balance) {
        this.uma_balance = uma_balance;
    }

    public double getBat_balance() {
        return bat_balance;
    }

    public void setBat_balance(double bat_balance) {
        this.bat_balance = bat_balance;
    }

    public double getLtc_reserved() {
        return ltc_reserved;
    }

    public void setLtc_reserved(double ltc_reserved) {
        this.ltc_reserved = ltc_reserved;
    }

    public double getLink_reserved() {
        return link_reserved;
    }

    public void setLink_reserved(double link_reserved) {
        this.link_reserved = link_reserved;
    }

    public double getAave_reserved() {
        return aave_reserved;
    }

    public void setAave_reserved(double aave_reserved) {
        this.aave_reserved = aave_reserved;
    }

    public double getUma_reserved() {
        return uma_reserved;
    }

    public void setUma_reserved(double uma_reserved) {
        this.uma_reserved = uma_reserved;
    }

    public double getBat_reserved() {
        return bat_reserved;
    }

    public void setBat_reserved(double bat_reserved) {
        this.bat_reserved = bat_reserved;
    }

    public double getLtc_available() {
        return ltc_available;
    }

    public void setLtc_available(double ltc_available) {
        this.ltc_available = ltc_available;
    }

    public double getLink_available() {
        return link_available;
    }

    public void setLink_available(double link_available) {
        this.link_available = link_available;
    }

    public double getAave_available() {
        return aave_available;
    }

    public void setAave_available(double aave_available) {
        this.aave_available = aave_available;
    }

    public double getUma_available() {
        return uma_available;
    }

    public void setUma_available(double uma_available) {
        this.uma_available = uma_available;
    }

    public double getBat_available() {
        return bat_available;
    }

    public void setBat_available(double bat_available) {
        this.bat_available = bat_available;
    }

    public double getEtheur_fee() {
        return etheur_fee;
    }

    public void setEtheur_fee(double etheur_fee) {
        this.etheur_fee = etheur_fee;
    }

    public double getLtceur_fee() {
        return ltceur_fee;
    }

    public void setLtceur_fee(double ltceur_fee) {
        this.ltceur_fee = ltceur_fee;
    }

    public double getLinkeur_fee() {
        return linkeur_fee;
    }

    public void setLinkeur_fee(double linkeur_fee) {
        this.linkeur_fee = linkeur_fee;
    }

    public double getAaveeur_fee() {
        return aaveeur_fee;
    }

    public void setAaveeur_fee(double aaveeur_fee) {
        this.aaveeur_fee = aaveeur_fee;
    }

    public double getUmaeur_fee() {
        return umaeur_fee;
    }

    public void setUmaeur_fee(double umaeur_fee) {
        this.umaeur_fee = umaeur_fee;
    }

    public double getBateur_fee() {
        return bateur_fee;
    }

    public void setBateur_fee(double bateur_fee) {
        this.bateur_fee = bateur_fee;
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
