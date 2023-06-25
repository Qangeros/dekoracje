package com.example.dekoracje.controller;

import com.example.dekoracje.model.dto.OrdersFromCustomerDto;
import com.example.dekoracje.model.entity.OrdersFromCustomer;
import com.example.dekoracje.model.entity.Stock;
import com.example.dekoracje.service.AddressService;
import com.example.dekoracje.service.OrdersFromCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import com.example.dekoracje.controller.util.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ordersfromcustomer")
public class OrdersFromCustomerController {

    @Autowired
    private OrdersFromCustomerService ordersFromCustomerService;

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public String showOrdersFromCustomerPage() {
        return "ordersfromcustomer";
    }

    @GetMapping("/getbyid")
    @ResponseBody
    public OrdersFromCustomerDto getOrdersFromCustomer(@RequestParam(value="id", required = true) Long id) {
        OrdersFromCustomer ordersFromCustomer = ordersFromCustomerService.getOrdersFromCustomerById(id);
        return new OrdersFromCustomerDto(ordersFromCustomer);
    }

    @GetMapping("/getbystring")
    @ResponseBody
    public List<OrdersFromCustomerDto> getOrdersFromCustomer(@RequestParam(value="searchString", required = true) String searchString) {
        List<OrdersFromCustomer> ordersFromCustomers = ordersFromCustomerService.getOrdersFromCustomerBySearch(searchString);
        return ordersFromCustomers.stream()
                .map(OrdersFromCustomerDto::new)
                .toList();
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<OrdersFromCustomerDto> getAllOrdersFromCustomers() {
        List<OrdersFromCustomer> ordersFromCustomers = ordersFromCustomerService.getAllOrdersFromCustomerList();
        return ordersFromCustomers.stream()
                .map(OrdersFromCustomerDto::new)
                .toList();
    }

    @DeleteMapping("/deletebyid")
    @ResponseBody
    public ResponseEntity<ErrorResponse> deleteOrdersFromCustomer(@RequestParam(value="id", required = true) Long id) {
        try {
            ordersFromCustomerService.deleteOrdersFromCustomerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Nie można usunąć stanu magazynowego," +
                    " ponieważ jest on powiązany z innymi rekordami w bazie danych.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<OrdersFromCustomer> addOrdersFromCustomer(@RequestBody OrdersFromCustomer ordersFromCustomer) {
        OrdersFromCustomer savedOrdersFromCustomer = ordersFromCustomerService.saveOrdersFromCustomer(ordersFromCustomer);
        return new ResponseEntity<>(savedOrdersFromCustomer, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    @ResponseBody
    public ResponseEntity<ErrorResponse> updateOrdersFromCustomer(@RequestBody OrdersFromCustomerDto ordersFromCustomerDto) {
        try {
            ordersFromCustomerService.updateOrdersFromCustomer(ordersFromCustomerDto);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Nie można zaktualizować zamówienia," +
                    " ponieważ jest ono powiązane z innymi rekordami w bazie danych.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }

    }

}
