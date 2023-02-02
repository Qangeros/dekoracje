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
    private final CustomerDto customer;
    private final AddressDto address;
    private final DocumentDto document;
    private final Timestamp timestamp;
    private final Double price;
    private final Boolean completed;

    public OrdersFromCustomerDto(OrdersFromCustomer ordersFromCustomer) {
        this.id = ordersFromCustomer.getId();
        this.customer = new CustomerDto(ordersFromCustomer.getCustomer());
        this.address = new AddressDto(ordersFromCustomer.getAddress());
        this.document = new DocumentDto(ordersFromCustomer.getDocument());
        this.timestamp = ordersFromCustomer.getTimestamp();
        this.price = ordersFromCustomer.getPrice();
        this.completed = ordersFromCustomer.getCompleted();
    }
}