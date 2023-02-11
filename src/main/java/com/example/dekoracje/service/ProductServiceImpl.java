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

    @Autowired
    private DataValidatorService dataValidatorService;

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
        if (product.getName().length() > 50)
            throw new IllegalArgumentException("Nazwa produktu nie może być dłuższa niż 50 znaków");
        return productRepository.save(product); // TODO: po dataValidatorService powinno być
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
