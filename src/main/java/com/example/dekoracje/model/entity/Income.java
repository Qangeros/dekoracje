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
public class Income {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Double gross_income;

    @Column(nullable = false)
    private Double net_income;

    @OneToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_income_document"))
    private Document document;

}