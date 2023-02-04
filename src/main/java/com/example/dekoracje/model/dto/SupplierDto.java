package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.Supplier;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.Supplier} entity
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierDto implements Serializable {
    private final Long id;
    private final String name;
    private final Long addressId;
    private final String email;
    private final String phone;
    private final String nip;
    private final Long userId;
    private final String address;

    public SupplierDto(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.addressId = supplier.getAddress().getId();
        this.email = supplier.getEmail();
        this.phone = supplier.getPhone();
        this.nip = supplier.getNip();
        this.userId = supplier.getUser().getId();
        this.address = supplier.getAddress().getStreet() + ", " +
                supplier.getAddress().getCity() + " " + supplier.getAddress().getPostalCode();
    }

    public SupplierDto(String name, String email, String phone, String nip, String address) {
        this.id = null;
        this.name = name;
        this.addressId = null;
        this.email = email;
        this.phone = phone;
        this.nip = nip;
        this.userId = null;
        this.address = address;
    }
}