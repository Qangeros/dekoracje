package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.ProductInOrder;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ProductInOrder} entity
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductInOrderDto implements Serializable {
    private final Long id;
    private final Long productId;
    private final Integer amount;
    private final Double price;
    private final Long ordersFromSupplierId;
    private final String productName;
    private final String supplierName;

    public ProductInOrderDto(ProductInOrder productInOrder) {
        this.id = productInOrder.getId();
        this.productId = productInOrder.getProduct().getId();
        this.amount = productInOrder.getAmount();
        this.price = productInOrder.getPrice();
        this.ordersFromSupplierId = null;
        this.productName = productInOrder.getProduct().getName();
        this.supplierName = productInOrder.getProduct().getSupplier().getName();
    }

    public ProductInOrderDto(Long id, Long productId, Integer amount, Double price, String productName, String supplierName) {
        this.id = id;
        this.productId = productId;
        this.amount = amount;
        this.price = price;
        this.ordersFromSupplierId = null;
        this.productName = productName;
        this.supplierName = supplierName;
    }
}