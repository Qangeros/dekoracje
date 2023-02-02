package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.dto.DocumentDto;
import com.example.dekoracje.model.dto.SupplierDto;
import com.example.dekoracje.model.entity.OrdersFromSupplier;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * A DTO for the {@link OrdersFromSupplier} entity
 */
@Data
public class OrdersFromSupplierDto implements Serializable {
    private final Long id;
    private final String order_number;
    private final SupplierDto supplier;
    private final DocumentDto document;
    private final Double price;
    private final Timestamp timestamp;
    private final Boolean is_delivered;

    public OrdersFromSupplierDto(OrdersFromSupplier ordersFromSupplier) {
        this.id = ordersFromSupplier.getId();
        this.order_number = ordersFromSupplier.getOrder_number();
        this.supplier = new SupplierDto(ordersFromSupplier.getSupplier());
        this.document = new DocumentDto(ordersFromSupplier.getDocument());
        this.price = ordersFromSupplier.getPrice();
        this.timestamp = ordersFromSupplier.getTimestamp();
        this.is_delivered = ordersFromSupplier.getIs_delivered();
    }
}