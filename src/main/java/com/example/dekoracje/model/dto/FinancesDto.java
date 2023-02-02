package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.Finances;
import lombok.Data;

import java.io.Serializable;
import java.security.Timestamp;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.Finances} entity
 */
@Data
public class FinancesDto implements Serializable {
    private final Long id;
    private final Double gross_value;
    private final Double net_value;
    private final Timestamp timestamp;


    public FinancesDto(Finances finances) {
        this.id = finances.getId();
        this.gross_value = finances.getGross_value();
        this.net_value = finances.getNet_value();
        this.timestamp = finances.getTimestamp();
    }
}