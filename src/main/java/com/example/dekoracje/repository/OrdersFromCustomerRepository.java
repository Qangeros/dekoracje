package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.OrdersFromCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersFromCustomerRepository extends JpaRepository<OrdersFromCustomer, Long> {
}