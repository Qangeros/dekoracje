package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.Document;
import lombok.Data;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.Document} entity
 */
@Data
public class DocumentDto implements Serializable {
    private final Long id;
    private final String name;
    private final Timestamp date;
    private final Double price;
    private final Boolean is_purchase;

    public DocumentDto(Document document) {
        this.id = document.getId();
        this.name = document.getName();
        this.date = document.getDate();
        this.price = document.getPrice();
        this.is_purchase = document.getIs_purchase();
    }
}