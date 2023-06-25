package com.example.dekoracje.service;

import com.example.dekoracje.model.dto.OrdersFromCustomerDto;
import com.example.dekoracje.model.entity.OrdersFromCustomer;
import com.example.dekoracje.repository.OrdersFromCustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersFromCustomerServiceImpl implements OrdersFromCustomerService {

    @Autowired
    private OrdersFromCustomerRepository ordersFromCustomerRepository;

    @Override
    public List<OrdersFromCustomer> getAllOrdersFromCustomerList() {
        return ordersFromCustomerRepository.findAll();
    }

    @Override
    public OrdersFromCustomer getOrdersFromCustomerById(Long id) {
        return ordersFromCustomerRepository.findById(id).orElseThrow();
    }

    @Override
    public OrdersFromCustomer saveOrdersFromCustomer(OrdersFromCustomer ordersFromCustomer) {
        return ordersFromCustomerRepository.save(ordersFromCustomer);
    }

    @Override
    public List<OrdersFromCustomer> getOrdersFromCustomerBySearch(String searchString) {
        return ordersFromCustomerRepository.findOrdersFromCustomerBySearch(searchString).orElseThrow();
    }

    @Override
    public void deleteOrdersFromCustomerById(Long id) {
        ordersFromCustomerRepository.deleteById(id);
    }

    @Override
    public void updateOrdersFromCustomer(OrdersFromCustomerDto ordersFromCustomerDto) {
        ordersFromCustomerRepository.updateOrdersFromCustomer(ordersFromCustomerDto);
    }

}
