package com.example.dekoracje.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.security.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table()
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Timestamp date;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Boolean is_purchase;

    @OneToOne
    @JoinColumn(name = "finances_id", foreignKey = @ForeignKey(name = "fk_document_finances"))
    private Finances finances;


}