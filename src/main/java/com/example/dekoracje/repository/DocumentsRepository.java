package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentsRepository extends JpaRepository<Document, Long> {

//    Optional<List<Document>> findDocumentsBySearch(String searchString);
}