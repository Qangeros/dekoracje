package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Address;
import com.example.dekoracje.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomerList();
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    void deleteCustomerById(Long id);
}
