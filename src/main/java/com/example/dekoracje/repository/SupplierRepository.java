package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query("SELECT s FROM Supplier s " +
            "WHERE s.name LIKE '%' || :searchString || '%' " +
            "OR s.nip LIKE '%' || :searchString || '%' " +
            "OR s.email LIKE '%' || :searchString || '%' ")
    Optional<List<Supplier>> findSupplierBySearch(String searchString);
}

