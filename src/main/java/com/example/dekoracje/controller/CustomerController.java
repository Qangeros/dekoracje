package com.example.dekoracje.controller;

import com.example.dekoracje.model.dto.CustomerDto;
import com.example.dekoracje.model.entity.Address;
import com.example.dekoracje.model.entity.Customer;
import com.example.dekoracje.repository.CustomerRepository;
import com.example.dekoracje.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String showCustomerPage() {
        return "customer";
    }

    @GetMapping("getbyid")
    @ResponseBody
    public CustomerDto getCustomer(@RequestParam(value="id", required = true) Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new CustomerDto(customer);
    }

    @GetMapping("/getallcustomers")
    @ResponseBody
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomerList();
        return customers.stream()
                .map(CustomerDto::new)
                .toList();
    }

    @DeleteMapping("{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }

    record NewCustomerRequest(String name, String surname, String email, String phone, String address) { }

    @PostMapping
    public Customer addCustomer(@RequestBody NewCustomerRequest request) {
        Customer customer = new Customer();
        Address address = new Address();

        customer.setName(request.name);
        customer.setSurname(request.surname);
        customer.setEmail(request.email);
        customer.setPhone(request.phone);
//        customer.setAddress(request.address);
        return customerRepository.save(customer);
    }

}
