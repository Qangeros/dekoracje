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
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name="fk_stock_product"))
    private Product product;

    @Column(nullable = false)
    private Integer amount;
}