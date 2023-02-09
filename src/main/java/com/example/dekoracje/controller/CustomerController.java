package com.example.dekoracje.controller;

import com.example.dekoracje.controller.util.ErrorResponse;
import com.example.dekoracje.model.dto.CustomerDto;
import com.example.dekoracje.model.entity.Customer;
import com.example.dekoracje.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String showCustomerPage() {
        return "customer";
    }

    @GetMapping("/getbyid")
    @ResponseBody
    public CustomerDto getCustomer(@RequestParam(value="id", required = true) Long id) {
        Customer customer = customerService.getCustomerById(id);
        return new CustomerDto(customer);
    }

    @GetMapping("/getbystring")
    @ResponseBody
    public List<CustomerDto> getCustomer(@RequestParam(value="searchString", required = true) String searchString) {
        List<Customer> customers = customerService.getCustomerBySearch(searchString);
        return customers.stream()
                .map(c -> new CustomerDto(c.getName(), c.getSurname(), c.getPhone(), c.getEmail(),
                        c.getAddress().getStreet() + ", "
                                + c.getAddress().getCity() + ", "
                                + c.getAddress().getPostalCode()))
                .toList();
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomerList();
        return customers.stream()
                .map(CustomerDto::new)
                .toList();
    }

    @PostMapping("/add")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid")
    @ResponseBody
    public ResponseEntity<ErrorResponse> deleteCustomer(@RequestParam(value="id", required = true) Long id) {
        try {
            customerService.deleteCustomerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Nie można usunąć klienta," +
                    " ponieważ jest on powiązany z innymi rekordami w bazie danych.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }


}
