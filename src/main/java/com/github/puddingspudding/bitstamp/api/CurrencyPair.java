package com.github.puddingspudding.bitstamp.api;

public enum CurrencyPair {

    ETHEUR,

    XLMEUR,

    BTCEUR,

    XRPEUR,

    LTCEUR,

    LINKEUR;

    public String toName() {
        return this.name().toLowerCase();
    }

}
