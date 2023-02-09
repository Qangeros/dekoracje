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
    private final String orderNumber;
    private final Long documentId;
    private final Double price;
    private final Timestamp timestamp;
    private final Boolean isDelivered;

    public OrdersFromSupplierDto(OrdersFromSupplier ordersFromSupplier) {
        this.id = ordersFromSupplier.getId();
        this.orderNumber = ordersFromSupplier.getOrderNumber();
        this.documentId = ordersFromSupplier.getDocument().getId();
        this.price = ordersFromSupplier.getPrice();
        this.timestamp = ordersFromSupplier.getTimestamp();
        this.isDelivered = ordersFromSupplier.getIsDelivered();
    }
}