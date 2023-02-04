package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s " +
            "WHERE s.product.name LIKE '%' || :searchString || '%' ") // TODO sprawdzić czy działa w ten sposób
    Optional<List<Stock>> findStockBySearch(String searchString);

    @Modifying
    @Query("UPDATE Stock s SET s.amount = :amount WHERE s.id = :id")
    void updateStockAmount(@Param("id") Long id, @Param("amount") int amount);
}