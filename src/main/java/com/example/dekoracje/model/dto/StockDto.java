package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.dto.ProductDto;
import com.example.dekoracje.model.entity.Stock;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Stock} entity
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StockDto implements Serializable {
    private final Long id;
    private final Long productId;
    private final String productName;
    private final Integer amount;

    public StockDto(Stock stock) {
        this.id = stock.getId();
        this.productId = stock.getProduct().getId();
        this.productName = stock.getProduct().getName();
        this.amount = stock.getAmount();
    }

    public StockDto(Long id, String productName, Integer amount) {
        this.id = id;
        this.productId = null;
        this.productName = productName;
        this.amount = amount;
    }
}