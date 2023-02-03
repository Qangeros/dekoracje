package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Document;

import java.util.List;

public interface DocumentService {

    List<Document> getAllDocumentList();
    Document getDocumentById(Long id);
    List<Document> getDocumentBySearch(String searchString);
    Document saveDocument(Document document);
    void deleteDocumentById(Long id);
}
