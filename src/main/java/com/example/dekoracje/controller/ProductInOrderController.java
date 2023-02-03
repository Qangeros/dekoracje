package com.example.dekoracje.controller;

import com.example.dekoracje.model.dto.ProductInOrderDto;
import com.example.dekoracje.model.entity.ProductInOrder;
import com.example.dekoracje.service.ProductInOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/productinorder")
public class ProductInOrderController {

    @Autowired
    private ProductInOrderService productInOrderService;

    @RequestMapping("")
    public String showProductInOrderPage() {
        return "cart";
    }

    @GetMapping("/getall") //TODO: TU OSTATNIO SKOŃCZYŁEŚ I TO POPRAW BO JEST DTO I ENTITY
    @ResponseBody
    public List<ProductInOrderDto> getAllProductInOrders() {
        List<ProductInOrder> productInOrders = productInOrderService.getAllProductInOrderList();
        return productInOrders.stream()
                .map(ProductInOrderDto::new)
                .toList();
    }


}
