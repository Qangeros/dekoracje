package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Customer;
import com.example.dekoracje.repository.AddressRepository;
import com.example.dekoracje.repository.CustomerRepository;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DataValidatorService dataValidatorService;

    @Override
    public List<Customer> getAllCustomerList() {
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            customer.setAddress(addressRepository.findById(customer.getAddress().getId()).orElseThrow());
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Customer> getCustomerBySearch(String searchString) {
        return customerRepository.findCustomerBySearch(searchString).orElseThrow();
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        if (!dataValidatorService.isEmailValid(customer.getEmail())) {
            throw new IllegalArgumentException("Niepoprawny adres email");
        }
        if (!dataValidatorService.isPhoneValid(customer.getPhone())) {
            throw new IllegalArgumentException("Niepoprawny numer telefonu");
        }
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
