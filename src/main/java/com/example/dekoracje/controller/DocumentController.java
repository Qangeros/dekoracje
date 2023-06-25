package com.example.dekoracje.controller;

import com.example.dekoracje.controller.util.ErrorResponse;
import com.example.dekoracje.model.dto.DocumentDto;
import com.example.dekoracje.model.entity.Document;
import com.example.dekoracje.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("")
    public String showDocumentPage(){
        return "document";
    }

    @GetMapping("/getbyid")
    @ResponseBody
    public DocumentDto getDocument(@RequestParam(value="id", required = true) Long id) {
        Document document = documentService.getDocumentById(id);
        return new DocumentDto(document);
    }

    @GetMapping("/getbystring")
    @ResponseBody
    public List<DocumentDto> getDocument(@RequestParam(value="searchString", required = true) String searchString) {
        List<Document> documents = documentService.getDocumentBySearch(searchString);
        return documents.stream()
                .map(DocumentDto::new)
                .toList();
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<DocumentDto> getAllDocuments() {
        List<Document> documents = documentService.getAllDocumentList();
        return documents.stream()
                .map(DocumentDto::new)
                .toList();
    }

    @PostMapping("/add")
    public ResponseEntity<Document> addDocument(@RequestBody Document document) {
        Document savedDocument = documentService.saveDocument(document);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid")
    @ResponseBody
    public ResponseEntity deleteDocument(@RequestParam(value="id", required = true) Long id) {
        try {
            documentService.deleteDocumentById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Nie można usunąć dokumentu," +
                    " ponieważ jest on powiązany z innymi rekordami w bazie danych.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }


}
