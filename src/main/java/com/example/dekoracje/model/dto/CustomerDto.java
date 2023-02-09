package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.Customer;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.Customer} entity
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto implements Serializable {
    private final Long id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final Long addressId;
    private final Long userId;
    private final String address;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.addressId = customer.getAddress().getId();
        this.userId = customer.getUser().getId();
        this.address = customer.getAddress().getStreet() + ", " +
                customer.getAddress().getCity() + " " + customer.getAddress().getPostalCode();
    }
}