package com.example.dekoracje.controller;

import com.example.dekoracje.repository.DocumentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private DocumentsRepository documentsRepository;

    @GetMapping("")
    public String showDocumentPage(){
        return "document";
    }



}
