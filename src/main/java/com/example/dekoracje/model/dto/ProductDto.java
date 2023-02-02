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
    private final SupplierDto id_supplier;
    private final String name;
    private final Double price;


    public ProductDto(Product product) {
        this.id = product.getId();
        this.id_supplier = new SupplierDto(product.getId_supplier());
        this.name = product.getName();
        this.price = product.getPrice();
    }
}