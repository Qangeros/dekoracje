package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.OrdersFromCustomer;
import lombok.Data;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.OrdersFromCustomer} entity
 */
@Data
public class OrdersFromCustomerDto implements Serializable {
    private final Long id;
    private final Long customerId;
    private final Long addressId;
    private final Long documentId;
    private final Timestamp timestamp;
    private final Double price;
    private final Boolean completed;

    public OrdersFromCustomerDto(OrdersFromCustomer ordersFromCustomer) {
        this.id = ordersFromCustomer.getId();
        this.customerId = ordersFromCustomer.getCustomer().getId();
        this.addressId = ordersFromCustomer.getAddress().getId();
        this.documentId = ordersFromCustomer.getDocument().getId();
        this.timestamp = ordersFromCustomer.getTimestamp();
        this.price = ordersFromCustomer.getPrice();
        this.completed = ordersFromCustomer.getCompleted();
    }
}