package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.Supplier;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.Supplier} entity
 */
@Data
public class SupplierDto implements Serializable {
    private final Long id;
    private final String name;
    private final AddressDto address;
    private final String email;
    private final String phone;
    private final String nip;
    private final UserTableDto user;

    public SupplierDto(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.address = new AddressDto(supplier.getAddress());
        this.email = supplier.getEmail();
        this.phone = supplier.getPhone();
        this.nip = supplier.getNip();
        this.user = new UserTableDto(supplier.getUser());
    }
}