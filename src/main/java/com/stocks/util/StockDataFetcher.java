package com.stocks.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.model.StockPrice;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ClassicHttpRequest;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class StockDataFetcher {
    private static final Logger logger = LoggerFactory.getLogger(StockDataFetcher.class);
    private static final String API_BASE_URL = "https://query1.finance.yahoo.com/v10/finance/quoteSummary/";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public List<StockPrice> fetchStockData(String symbol, LocalDate startDate, LocalDate endDate) throws Exception {
        List<StockPrice> prices = new ArrayList<>();
        
        try {
            // Mock data generation for demonstration
            // In production, integrate with a real API like Yahoo Finance, Alpha Vantage, or IEX Cloud
            prices = generateMockData(symbol, startDate, endDate);
            logger.info("Generated mock data for symbol: {} from {} to {}", symbol, startDate, endDate);
        } catch (Exception e) {
            logger.error("Error fetching stock data for symbol: {}", symbol, e);
            throw new Exception("Failed to fetch stock data", e);
        }

        return prices;
    }

    private List<StockPrice> generateMockData(String symbol, LocalDate startDate, LocalDate endDate) {
        List<StockPrice> prices = new ArrayList<>();
        LocalDate currentDate = startDate;
        double basePrice = 100.0 + (symbol.hashCode() % 200); // Pseudo-random base price

        while (! currentDate.isAfter(endDate)) {
            // Skip weekends
            if (currentDate.getDayOfWeek().getValue() < 6) {
                double randomVariation = (Math.random() - 0.5) * 10; // ±5 variation
                double open = basePrice + randomVariation;
                double high = open + Math.abs(Math.random() * 5);
                double low = open - Math.abs(Math.random() * 5);
                double close = (high + low) / 2;
                long volume = 1000000 + (long)(Math.random() * 9000000);

                prices.add(new StockPrice(currentDate, open, high, low, close, volume));
                basePrice = close; // Next day's base is today's close
            }
            currentDate = currentDate.plusDays(1);
        }

        return prices;
    }
}