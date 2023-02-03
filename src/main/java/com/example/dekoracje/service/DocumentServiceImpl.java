package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Document;
import com.example.dekoracje.repository.AddressRepository;
import com.example.dekoracje.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public List<Document> getAllDocumentList() {
        return documentRepository.findAll();
    }

    @Override
    public Document getDocumentById(Long id) {
        return documentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Document> getDocumentBySearch(String searchString) {
        return documentRepository.findDocumentsBySearch(searchString).orElseThrow();
    }

    @Override
    public Document saveDocument(Document document) { // TODO: sprawdzić czy nie trzeba dodać walidacji
        return documentRepository.save(document);
    }

    @Override
    public void deleteDocumentById(Long id) {
        documentRepository.deleteById(id);
    }
}
