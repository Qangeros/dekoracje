package com.example.dekoracje.service;

import com.example.dekoracje.model.dto.OrdersFromCustomerDto;
import com.example.dekoracje.model.entity.OrdersFromCustomer;

import java.util.List;

public interface OrdersFromCustomerService {
    List<OrdersFromCustomer> getAllOrdersFromCustomerList();
    OrdersFromCustomer getOrdersFromCustomerById(Long id);
    OrdersFromCustomer saveOrdersFromCustomer(OrdersFromCustomer ordersFromCustomer);
    List<OrdersFromCustomer> getOrdersFromCustomerBySearch(String searchString);
    void deleteOrdersFromCustomerById(Long id);
    void updateOrdersFromCustomer(OrdersFromCustomerDto ordersFromCustomerDto);
}
