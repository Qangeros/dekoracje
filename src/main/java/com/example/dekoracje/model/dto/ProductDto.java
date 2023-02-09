package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Product} entity
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto implements Serializable {
    private final Long id;
    private final Long supplierId;
    private final String supplierName;
    private final String name;
    private final Double price;
    private final String type;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.supplierId = product.getSupplier().getId();
        this.supplierName = product.getSupplier().getName();
        this.name = product.getName();
        this.price = product.getPrice();
        this.type = product.getType();
    }

    public ProductDto(String name, Double price, Long supplierId, String type) {
        this.id = null;
        this.supplierId = supplierId;
        this.supplierName = null;
        this.name = name;
        this.price = price;
        this.type = type;
    }
}