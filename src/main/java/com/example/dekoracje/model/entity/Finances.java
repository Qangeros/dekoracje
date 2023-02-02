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
public class Finances {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    Double gross_value;

    @Column(nullable = false)
    Double net_value;

    @Column(nullable = false)
    Timestamp timestamp;


}