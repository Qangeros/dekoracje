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
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name="fk_supplier_address"))
    private Address address;

    @Column()
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column()
    private String nip;

    @OneToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(name="fk_supplier_user"))
    private UserTable user;

}
