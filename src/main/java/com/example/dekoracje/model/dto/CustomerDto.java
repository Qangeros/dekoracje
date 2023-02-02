package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.Customer;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.Customer} entity
 */
@Data
public class CustomerDto implements Serializable {
    private final Long id;
    private final String name;
    private final String surname;
    private final String email;
    private final String phone;
    private final AddressDto address;
    private final UserTableDto user;

    public CustomerDto(Customer customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.surname = customer.getSurname();
        this.email = customer.getEmail();
        this.phone = customer.getPhone();
        this.address = new AddressDto(customer.getAddress());
        this.user = new UserTableDto(customer.getUser());
    }
}