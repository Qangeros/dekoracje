package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Stock;
import com.example.dekoracje.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Override
    public List<Stock> getAllStockList() {
        return stockRepository.findAll();
    }

    @Override
    public Stock getStockById(Long id) {
        return stockRepository.findById(id).orElseThrow();
    }

    @Override
    public Stock saveStock(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> getStockBySearch(String searchString) {
        return stockRepository.findStockBySearch(searchString).orElseThrow();
    }

    @Override
    public void deleteStockById(Long id) {
        stockRepository.deleteById(id);
    }

    @Override
    public void updateStockAmount(Stock stock) {
        stockRepository.updateStockAmount(stock.getId(), stock.getAmount());
    }

}
