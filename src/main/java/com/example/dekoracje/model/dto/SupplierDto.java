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
    private final Long addressId;
    private final String email;
    private final String phone;
    private final String nip;
    private final Long userId;

    public SupplierDto(Supplier supplier) {
        this.id = supplier.getId();
        this.name = supplier.getName();
        this.addressId = supplier.getAddress().getId();
        this.email = supplier.getEmail();
        this.phone = supplier.getPhone();
        this.nip = supplier.getNip();
        this.userId = supplier.getUser().getId();
    }
}