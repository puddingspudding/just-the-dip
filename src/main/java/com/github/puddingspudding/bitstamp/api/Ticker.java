package com.github.puddingspudding.bitstamp.api;

import com.google.gson.Gson;

public class Ticker {

    private double high;
    private double last;
    private long timestamp;
    private double bid;
    private double vwap;
    private double volume;
    private double low;
    private double ask;
    private double open;


    public double getHigh() {
        return high;
    }

    public double getLast() {
        return last;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getBid() {
        return bid;
    }

    public double getVwap() {
        return vwap;
    }

    public double getVolume() {
        return volume;
    }

    public double getLow() {
        return low;
    }

    public double getAsk() {
        return ask;
    }

    public double getOpen() {
        return open;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public void setLast(double last) {
        this.last = last;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public void setVwap(double vwap) {
        this.vwap = vwap;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }


    public void setOpen(double open) {
        this.open = open;
    }


    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
