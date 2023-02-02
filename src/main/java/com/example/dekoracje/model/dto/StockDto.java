package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.dto.ProductDto;
import com.example.dekoracje.model.entity.Stock;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Stock} entity
 */
@Data
public class StockDto implements Serializable {
    private final Long id;
    private final ProductDto id_product;
    private final Integer amount;

    public StockDto(Stock stock) {
        this.id = stock.getId();
        this.id_product = new ProductDto(stock.getId_product());
        this.amount = stock.getAmount();
    }
}