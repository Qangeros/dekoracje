package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.UserType;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.UserType} entity
 */
@Data
public class UserTypeDto implements Serializable {
    private final Long id;
    private final String name;

    public UserTypeDto(UserType userType) {
        this.id = userType.getId();
        this.name = userType.getName();
    }
}