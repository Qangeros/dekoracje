package com.example.dekoracje.controller;

import com.example.dekoracje.model.Customer;
import com.example.dekoracje.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("{id}")
    public Customer getCustomer(@PathVariable Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    record NewCustomerRequest(String name, String surname, String email, String phone, String address) { }

    @PostMapping
    public Customer addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        customer.setName(request.name);
        customer.setSurname(request.surname);
        customer.setEmail(request.email);
        customer.setPhone(request.phone);
        customer.setAddress(request.address);
        return customerRepository.save(customer);
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }

}
