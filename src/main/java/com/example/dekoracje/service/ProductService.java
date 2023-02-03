package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProductList();
    Product getProductById(Long id);
    List<Product> getProductBySearch(String searchString);
    Product saveProduct(Product product);
    void deleteProductById(Long id);

}
