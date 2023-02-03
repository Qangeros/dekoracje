package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.dto.SupplierDto;
import com.example.dekoracje.model.entity.Product;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Product} entity
 */
@Data
public class ProductDto implements Serializable {
    private final Long id;
    private final Long supplierId;
    private final String name;
    private final Double price;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.supplierId = product.getSupplier().getId();
        this.name = product.getName();
        this.price = product.getPrice();
    }
}