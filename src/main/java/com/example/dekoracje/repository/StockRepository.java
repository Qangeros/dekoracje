package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s " +
            "WHERE s.name LIKE '%' || :searchString || '%' " +
            "OR s.description LIKE '%' || :searchString || '%' " +
            "OR s.price LIKE '%' || :searchString || '%'")
    Optional<List<Stock>> findStockBySearch(String searchString);
}