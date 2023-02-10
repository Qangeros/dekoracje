package com.example.dekoracje.controller;

import com.example.dekoracje.controller.util.ErrorResponse;
import com.example.dekoracje.model.dto.ProductInOrderDto;
import com.example.dekoracje.model.dto.StockDto;
import com.example.dekoracje.model.entity.Customer;
import com.example.dekoracje.model.entity.Product;
import com.example.dekoracje.model.entity.ProductInOrder;
import com.example.dekoracje.service.ProductInOrderService;
import com.example.dekoracje.service.ProductService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class ProductInOrderController {

    @Autowired
    private ProductInOrderService productInOrderService;

    @Autowired
    private ProductService productService;

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

//    @PostMapping("/update")
//    public ResponseEntity<ProductInOrder> updateProductInOrder(@RequestParam(value="id", required = true) Long id,
//                                                         @RequestParam(value="amount", required = true) Integer amount) {
//        ProductInOrder productInOrder = productInOrderService.getProductInOrderById(id);
//        productInOrder.setAmount(amount);
//        productInOrderService.updateProductInOrder(id, amount);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }

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


}
