package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.UserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserTable, Long> {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<UserTable> findByUsername(String username);
}