package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.Address;
import lombok.*;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.Address} entity
 */
@Data
public class AddressDto implements Serializable {
    private final Long id;
    private final String street;
    private final String city;
    private final String postalCode;
    private final Boolean isWorkplace;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
        this.postalCode = address.getPostalCode();
        this.isWorkplace = address.getIsWorkplace();
    }
}

