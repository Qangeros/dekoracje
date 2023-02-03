package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.dto.OrdersFromSupplierDto;
import com.example.dekoracje.model.dto.ProductDto;
import com.example.dekoracje.model.entity.ProductInOrder;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link ProductInOrder} entity
 */
@Data
public class ProductInOrderDto implements Serializable {
    private final Long id;
    private final Long productId;
    private final Integer amount;
    private final Double price;
    private final Long ordersFromSupplierId;

    public ProductInOrderDto(ProductInOrder productInOrder) {
        this.id = productInOrder.getId();
        this.productId = productInOrder.getProduct().getId();
        this.amount = productInOrder.getAmount();
        this.price = productInOrder.getPrice();
        this.ordersFromSupplierId = productInOrder.getOrdersFromSupplier().getId();
    }
}