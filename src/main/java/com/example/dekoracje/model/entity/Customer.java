package com.example.dekoracje.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column()
    private String email;

    @Column(nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name="fk_customer_address"))
    private Address address;

    @OneToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name = "fk_customer_user"))
    private UserTable user;

}
