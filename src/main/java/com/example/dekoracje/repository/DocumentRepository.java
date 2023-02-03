package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    @Query("SELECT d FROM Document d WHERE " +
            "   d.name LIKE '%' ||" + " :searchString || '%' ")
    Optional<List<Document>> findDocumentsBySearch(String searchString);
}