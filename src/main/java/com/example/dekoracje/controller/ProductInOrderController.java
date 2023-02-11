package com.example.dekoracje.controller;

import com.example.dekoracje.controller.util.ErrorResponse;
import com.example.dekoracje.model.dto.ProductInOrderDto;
import com.example.dekoracje.model.entity.OrdersFromSupplier;
import com.example.dekoracje.model.entity.Product;
import com.example.dekoracje.model.entity.ProductInOrder;
import com.example.dekoracje.model.entity.Stock;
import com.example.dekoracje.service.OrdersFromSupplierService;
import com.example.dekoracje.service.ProductInOrderService;
import com.example.dekoracje.service.ProductService;
import com.example.dekoracje.service.StockService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/cart")
public class ProductInOrderController {

    @Autowired
    private ProductInOrderService productInOrderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrdersFromSupplierService ordersFromSupplierService;

    @Autowired
    private StockService stockService;

    @RequestMapping("")
    public String showProductInOrderPage() {
        return "cart";
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<ProductInOrderDto> getAllProductInOrders() {
        List<ProductInOrder> productInOrders = productInOrderService.getAllCartProducts();
        return productInOrders.stream()
                .map(ProductInOrderDto::new)
                .toList();
    }

    @PostMapping("/add")
    public ResponseEntity<ProductInOrder> addCustomer(@RequestParam(value="id", required = true) Long id) {
        Product product = productService.getProductById(id);
        if(productInOrderService.isProductInCart(id)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        ProductInOrderDto dto = new ProductInOrderDto(0L, product.getId(), 1, product.getPrice(),
                product.getName(), product.getSupplier().getName());

        ProductInOrder savedProduct = productInOrderService.saveProductInOrder(
                new ProductInOrder(0L, productService.getProductById(dto.getProductId()),
                        dto.getAmount(), dto.getPrice(), null)
                );
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PostMapping("/deletebyid")
    public ResponseEntity<ProductInOrder> deleteProductInOrder(@RequestParam(value="id", required = true) Long id) {
        productInOrderService.deleteProductInOrderById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/updatelist")
    public ResponseEntity<ErrorResponse> updateCartAmountList(@RequestBody List<ProductInOrderDto> dtos) {
        try {
            dtos.forEach(s -> productInOrderService.updateProductInOrder(s.getId(), s.getAmount()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Wystąpił błąd podczas aktualizacji stanu magazynowego.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    @Transactional
    @PutMapping("/updateofs")
    public ResponseEntity<ErrorResponse> updateCartOfs() {
        List<ProductInOrder> pio = productInOrderService.getAllCartProducts();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Double price = pio.stream()
                .mapToDouble(s -> s.getPrice() * s.getAmount())
                .sum();
        OrdersFromSupplier ofs = ordersFromSupplierService.saveOrdersFromSupplier(
                new OrdersFromSupplier(0L, ordersFromSupplierService.generateOrderNumber(), null,
                        price, timestamp, false));
        try {
            pio.forEach(s -> {
                s.setOrdersFromSupplier(ofs);
                productInOrderService.updateProductInOrder(s);
                try {
                    if (stockService.getStockById(s.getProduct().getId()) != null) {
                        stockService.updateStockAmount(s.getProduct().getId(),
                                s.getAmount() + stockService.getStockById(s.getProduct().getId()).getAmount());
                    }
                } catch (NoSuchElementException e) {
                    stockService.saveStock(new Stock(0L, s.getProduct(), s.getAmount()));
                }
            });
            return new ResponseEntity<>(HttpStatus.OK); // TODO: update i insert stocku
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Wystąpił błąd podczas aktualizacji stanu magazynowego.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }

    }

}
