package com.stocks.service;

import com.stocks.model.Stock;
import com.stocks.model.StockPrice;
import com.stocks.util.StockDataFetcher;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class StockService {

    private final StockDataFetcher dataFetcher;

    public StockService() {
        this.dataFetcher = new StockDataFetcher();
    }

    public Stock analyzeStock(String symbol) throws Exception {
        // Get last 3 months of stock data
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusMonths(3);

        List<StockPrice> prices = dataFetcher.fetchStockData(symbol, startDate, endDate);

        if (prices.isEmpty()) {
            throw new RuntimeException("No data found for symbol: " + symbol);
        }

        // Calculate statistics
        Stock stock = new Stock();
        stock.setSymbol(symbol);
        stock.setPrices(prices);

        // Calculate current price
        StockPrice latestPrice = prices.get(prices.size() - 1);
        stock.setCurrentPrice(latestPrice.getClose());

        // Calculate min and max
        double minPrice = prices.stream()
                .mapToDouble(StockPrice::getLow)
                .min()
                .orElse(0);
        double maxPrice = prices.stream()
                .mapToDouble(StockPrice::getHigh)
                .max()
                .orElse(0);

        stock.setMinPrice(minPrice);
        stock.setMaxPrice(maxPrice);

        // Calculate average price
        double avgPrice = prices.stream()
                .mapToDouble(StockPrice::getClose)
                .average()
                .orElse(0);
        stock.setAvgPrice(avgPrice);

        // Calculate change percentage
        StockPrice firstPrice = prices.get(0);
        double changePercent = ((latestPrice.getClose() - firstPrice.getClose()) / firstPrice.getClose()) * 100;
        stock.setChangePercent(changePercent);

        return stock;
    }
}