package com.example.dekoracje.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.security.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders_from_customer")
public class OrdersFromCustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "fk_ofc_customer"))
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "address_id", foreignKey = @ForeignKey(name = "fk_ofc_address"))
    private Address address;

    @OneToOne
    @JoinColumn(name = "document_id", foreignKey = @ForeignKey(name = "fk_ofc_document"))
    private Document document; // możliwe, że do usunięcia

    @Column(nullable = false)
    private Timestamp timestamp;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Boolean completed;

}