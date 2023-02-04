package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Supplier;

import java.util.List;

public interface SupplierService {

    List<Supplier> getAllSupplierList();
    Supplier getSupplierById(Long id);
    Supplier saveSupplier(Supplier supplier);
    List<Supplier> getSupplierBySearch(String searchString);
    void deleteSupplierById(Long id);
}
