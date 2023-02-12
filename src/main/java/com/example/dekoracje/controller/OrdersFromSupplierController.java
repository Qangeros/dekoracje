package com.example.dekoracje.controller;

import com.example.dekoracje.service.OrdersFromSupplierService;
import com.example.dekoracje.service.ProductInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ofs")
public class OrdersFromSupplierController {

    @Autowired
    private OrdersFromSupplierService ordersFromSupplierService;

    @Autowired
    private ProductInOrderService productInOrderService;

    @RequestMapping("")
    public String showOrdersFromSupplierPage() {
        return "ordersFromSupplier";
    }

}
