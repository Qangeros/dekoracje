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
public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_pio_product"))
    private Product product;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Double price;

    @OneToOne
    @JoinColumn(nullable = true, foreignKey = @ForeignKey(name="fk_pio_ofs"))
    private OrdersFromSupplier ordersFromSupplier;


}