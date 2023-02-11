package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.OrdersFromSupplier;

public interface OrdersFromSupplierService {
    OrdersFromSupplier saveOrdersFromSupplier(OrdersFromSupplier ordersFromSupplier);
    String generateOrderNumber();

}
