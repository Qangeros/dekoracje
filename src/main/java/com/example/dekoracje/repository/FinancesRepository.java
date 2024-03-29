package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.Finances;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancesRepository extends JpaRepository<Finances, Long> {
}