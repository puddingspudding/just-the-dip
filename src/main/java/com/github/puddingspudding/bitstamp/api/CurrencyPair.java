package com.github.puddingspudding.bitstamp.api;

public enum CurrencyPair {

    ETHEUR,

    XLMEUR,

    BTCEUR,

    XRPEUR,

    LTCEUR,

    LINKEUR,

    AAVEEUR,

    UMAEUR,

    BATEUR;

    public String toName() {
        return this.name().toLowerCase();
    }

}
