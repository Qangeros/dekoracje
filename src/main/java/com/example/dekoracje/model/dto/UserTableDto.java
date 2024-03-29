package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.UserTable;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link UserTable} entity
 */
@Data
public class UserTableDto implements Serializable {
    private final Long id;
    private final String username;
    private final String email;
    private final String userType;

    public UserTableDto(UserTable userTable) {
        this.id = userTable.getId();
        this.username = userTable.getUsername();
        this.email = userTable.getEmail();
        this.userType = String.valueOf(userTable.getUserRole());
    }
}