package com.example.dekoracje.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table()
public class Outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Double gross_outcome;

    @Column(nullable = false)
    private Double net_outcome;

    @OneToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_outcome_document"))
    private Document id_document;
}