package com.github.puddingspudding.bitstamp.api;

public enum CurrencyPair {

    ETHEUR,

    XLMEUR,

    BTCEUR,

    XRPEUR;

    public String toName() {
        return this.name().toLowerCase();
    }

}
