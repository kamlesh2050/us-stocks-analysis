package com.stocks.model;

import lombok.Data;
import java.util.List;

@Data
public class Stock {
    private String symbol;
    private double currentPrice;
    private double minPrice;
    private double maxPrice;

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public void setMaxPrice(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setAvgPrice(double avgPrice) {
        this.avgPrice = avgPrice;
    }

    public void setChangePercent(double changePercent) {
        this.changePercent = changePercent;
    }

    public void setPrices(List<StockPrice> prices) {
        this.prices = prices;
    }

    private double avgPrice;

    public String getSymbol() {
        return symbol;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public double getAvgPrice() {
        return avgPrice;
    }

    public double getChangePercent() {
        return changePercent;
    }

    public List<StockPrice> getPrices() {
        return prices;
    }

    private double changePercent;
    private List<StockPrice> prices;
}