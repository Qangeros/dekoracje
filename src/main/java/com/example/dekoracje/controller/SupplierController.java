package com.example.dekoracje.controller;

import com.example.dekoracje.controller.util.ErrorResponse;
import com.example.dekoracje.model.dto.SupplierDto;
import com.example.dekoracje.model.entity.Supplier;
import com.example.dekoracje.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping("")
    public String showSupplierPage() {
        return "supplier";
    }

    @GetMapping("/getbyid")
    @ResponseBody
    public SupplierDto getSupplier(@RequestParam(value="id", required = true) Long id) {
        Supplier supplier = supplierService.getSupplierById(id);
        return new SupplierDto(supplier);
    }

    @GetMapping("/getbystring")
    @ResponseBody
    public List<SupplierDto> getSupplier(@RequestParam(value="searchString", required = true) String searchString) {
        List<Supplier> suppliers = supplierService.getSupplierBySearch(searchString);
        return suppliers.stream()
                .map(SupplierDto::new)
                .toList();
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<SupplierDto> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getAllSupplierList();

        return suppliers.stream()
                .map(SupplierDto::new)
                .toList();
    }

    @PostMapping("/add")
    public ResponseEntity<Supplier> addSupplier(@RequestBody Supplier supplier) {
        Supplier savedSupplier = supplierService.saveSupplier(supplier);
        return new ResponseEntity<>(savedSupplier, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid")
    @ResponseBody
    public ResponseEntity<ErrorResponse> deleteSupplier(@RequestParam(value="id", required = true) Long id) {
        try {
            supplierService.deleteSupplierById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Nie można usunąć dostawcy," +
                    " ponieważ jest on powiązany z innymi rekordami w bazie danych.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }
}
