package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.Type;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.Type} entity
 */
@Data
public class TypeDto implements Serializable {
    private final Long id;
    private final String name;
    private final Boolean is_product;

    public TypeDto(Type type) {
        this.id = type.getId();
        this.name = type.getName();
        this.is_product = type.getIs_product();
    }
}