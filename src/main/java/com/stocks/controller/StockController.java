package com.stocks.controller;

import com.stocks.model.Stock;
import com.stocks.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping("/analyze")
    @ResponseBody
    public Stock analyzeStock(@RequestParam String symbol) {
        try {
            return stockService.analyzeStock(symbol);
        } catch (Exception e) {
            throw new RuntimeException("Error analyzing stock: " + e.getMessage());
        }
    }

    @GetMapping("/api/stock/{symbol}")
    @ResponseBody
    public Stock getStockData(@PathVariable String symbol) {
        try {
            return stockService.analyzeStock(symbol.toUpperCase());
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving stock data: " + e.getMessage());
        }
    }
}