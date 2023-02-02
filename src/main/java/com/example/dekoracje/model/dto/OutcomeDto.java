package com.example.dekoracje.model.dto;

import com.example.dekoracje.model.dto.DocumentDto;
import com.example.dekoracje.model.entity.Outcome;
import lombok.Data;

import java.io.Serializable;

/**
 * A DTO for the {@link Outcome} entity
 */
@Data
public class OutcomeDto implements Serializable {
    private final Long id;
    private final Double gross_outcome;
    private final Double net_outcome;
    private final DocumentDto id_document;

    public OutcomeDto(Outcome outcome) {
        this.id = outcome.getId();
        this.gross_outcome = outcome.getGross_outcome();
        this.net_outcome = outcome.getNet_outcome();
        this.id_document = new DocumentDto(outcome.getId_document());
    }
}