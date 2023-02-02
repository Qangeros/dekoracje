package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.OrdersFromSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersFromSupplierRepository extends JpaRepository<OrdersFromSupplier, Long> {
}