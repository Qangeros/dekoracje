package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Product;
import com.example.dekoracje.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProductList() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> getProductBySearch(String searchString) {
        return productRepository.findProductBySearch(searchString).orElseThrow();
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product); // TODO: walidacja na długość nazwy?
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
