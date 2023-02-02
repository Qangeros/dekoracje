package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.name LIKE '%' ||" +
            " :searchString || '%' ")
    Optional<List<Customer>> findCustomerBySearch(String searchString);
}