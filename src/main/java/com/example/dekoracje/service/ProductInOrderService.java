package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.ProductInOrder;

import java.util.List;

public interface ProductInOrderService {

    List<ProductInOrder> getAllProductInOrderList();
    ProductInOrder getProductInOrderById(Long id);
    ProductInOrder saveProductInOrder(ProductInOrder productInOrder);
    void deleteProductInOrderById(Long id);
    List<ProductInOrder> saveAllProductInOrderList();
    void updateProductInOrder(Long id, Integer amount);
    void updateProductInOrder(ProductInOrder productInOrder);
    List<ProductInOrder> getAllCartProducts();
    boolean isProductInCart(Long id);

}
