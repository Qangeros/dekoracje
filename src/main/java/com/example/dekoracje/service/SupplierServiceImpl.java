package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Supplier;
import com.example.dekoracje.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private DataValidatorService dataValidatorService;

    @Override
    public List<Supplier> getAllSupplierList() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Supplier> getSupplierBySearch(String searchString) {
        return supplierRepository.findSupplierBySearch(searchString).orElseThrow();
    }

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplierById(Long id) {
        supplierRepository.deleteById(id);
    }
}
