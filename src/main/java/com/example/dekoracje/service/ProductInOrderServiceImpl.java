package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.ProductInOrder;
import com.example.dekoracje.repository.ProductInOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductInOrderServiceImpl implements ProductInOrderService {

    @Autowired
    private ProductInOrderRepository productInOrderRepository;

    @Override
    public List<ProductInOrder> getAllProductInOrderList() {
        return productInOrderRepository.findAll();
    }

    @Override
    public ProductInOrder getProductInOrderById(Long id) {
        return productInOrderRepository.findById(id).orElseThrow();
    }

    @Override
    public ProductInOrder saveProductInOrder(ProductInOrder productInOrder) {
        return productInOrderRepository.save(productInOrder);
    }

    @Override
    public void deleteProductInOrderById(Long id) {
        productInOrderRepository.deleteById(id);
    }

    @Override
    public List<ProductInOrder> saveAllProductInOrderList() {
        List<ProductInOrder> productInOrderList = getAllCartProducts();
        return productInOrderRepository.saveAll(productInOrderList);
    }

    @Override
    public void updateProductInOrder(Long id, Integer amount) {
        productInOrderRepository.updateProductInOrder(id, amount);
    }

    @Override
    public void updateProductInOrder(ProductInOrder productInOrder) {
        productInOrderRepository.updateProductInOrder(productInOrder);
    }

    @Override
    public List<ProductInOrder> getAllCartProducts() {
        return productInOrderRepository.getAllCartProducts();
    }

    @Override
    public boolean isProductInCart(Long id) {
        List<ProductInOrder> productInOrders = getAllCartProducts();
        for (ProductInOrder productInOrder : productInOrders) {
            if(productInOrder.getProduct().getId().equals(id)){
                return true;
            }
        }
        return false;
    }

}
