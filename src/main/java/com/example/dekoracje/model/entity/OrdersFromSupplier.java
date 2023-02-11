package com.example.dekoracje.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders_from_supplier")
public class OrdersFromSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @OneToOne
    @JoinColumn(name = "document_id", foreignKey = @ForeignKey(name = "fk_ofs_document"))
    private Document document;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "timestamp", nullable = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Timestamp timestamp;

    @Column(name = "is_delivered", nullable = false)
    private Boolean isDelivered;
}