package com.kafka.action.spark.offline.model;

import java.io.Serializable;

/**
 * @ClassName StockInfo
 * @Description TODO
 * @Author zeng.yakun (0178)
 * @Date 2018/11/1 15:27
 * @Version 1.0
 **/
public class StockInfo implements Serializable {

    private static final long serialVersionUID = -4971910745854427871L;

    private String stockName;

    private String stockCode;

    private String tradeDate;

    private double highPrice;

    private double lowPrice;

    private String lowPriceDate;

    private String highPriceDate;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getLowPriceDate() {
        return lowPriceDate;
    }

    public void setLowPriceDate(String lowPriceDate) {
        this.lowPriceDate = lowPriceDate;
    }

    public String getHighPriceDate() {
        return highPriceDate;
    }

    public void setHighPriceDate(String highPriceDate) {
        this.highPriceDate = highPriceDate;
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "stockName='" + stockName + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", tradeDate='" + tradeDate + '\'' +
                ", highPrice=" + highPrice +
                ", lowPrice=" + lowPrice +
                ", lowPriceDate='" + lowPriceDate + '\'' +
                ", highPriceDate='" + highPriceDate + '\'' +
                '}';
    }
}
