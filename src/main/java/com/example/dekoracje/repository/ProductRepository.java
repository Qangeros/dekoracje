package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE '%' || :searchString || '%'")
    Optional<List<Product>> findProductBySearch(String searchString);
}