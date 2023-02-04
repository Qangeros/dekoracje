package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Stock;

import java.util.List;

public interface StockService {

    List<Stock> getAllStockList();
    Stock getStockById(Long id);
    Stock saveStock(Stock stock);
    List<Stock> getStockBySearch(String searchString);
    void deleteStockById(Long id);
}
