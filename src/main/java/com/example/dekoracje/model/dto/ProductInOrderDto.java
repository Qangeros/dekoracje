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
    private final ProductDto id_product;
    private final Integer amount;
    private final Double price;
    private final OrdersFromSupplierDto id_order_fs;

    public ProductInOrderDto(ProductInOrder productInOrder) {
        this.id = productInOrder.getId();
        this.id_product = new ProductDto(productInOrder.getId_product());
        this.amount = productInOrder.getAmount();
        this.price = productInOrder.getPrice();
        this.id_order_fs = new OrdersFromSupplierDto(productInOrder.getId_order_fs());
    }
}