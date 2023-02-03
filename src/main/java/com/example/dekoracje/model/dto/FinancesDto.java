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
    private final Double grossValue;
    private final Double netValue;
    private final Timestamp timestamp;


    public FinancesDto(Finances finances) {
        this.id = finances.getId();
        this.grossValue = finances.getGrossValue();
        this.netValue = finances.getNetValue();
        this.timestamp = finances.getTimestamp();
    }
}