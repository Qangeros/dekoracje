package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.entity.Income;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.dekoracje.model.entity.Income} entity
 */
@Data
public class IncomeDto implements Serializable {
    private final Long id;
    private final Double gross_income;
    private final Double net_income;
    private final DocumentDto document;

    public IncomeDto(Income income) {
        this.id = income.getId();
        this.gross_income = income.getGross_income();
        this.net_income = income.getNet_income();
        this.document = new DocumentDto(income.getDocument());
    }
}