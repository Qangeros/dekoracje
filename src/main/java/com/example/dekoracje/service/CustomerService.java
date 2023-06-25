package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomerList();
    Customer getCustomerById(Long id);
    Customer saveCustomer(Customer customer);
    List<Customer> getCustomerBySearch(String searchString);
    void deleteCustomerById(Long id);
}
