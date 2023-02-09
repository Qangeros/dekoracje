package com.example.dekoracje.controller;

import com.example.dekoracje.controller.util.ErrorResponse;
import com.example.dekoracje.model.dto.ProductDto;
import com.example.dekoracje.model.entity.Product;
import com.example.dekoracje.model.entity.Supplier;
import com.example.dekoracje.service.ProductService;
import com.example.dekoracje.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private SupplierService supplierService;

    @GetMapping("")
    public String showProductPage() {
        return "product";
    }

    @GetMapping("/getbyid")
    @ResponseBody
    public ProductDto getProduct(@RequestParam(value="id", required = true) Long id) {
        Product product = productService.getProductById(id);
        return new ProductDto(product);
    }

    @GetMapping("/getbystring")
    @ResponseBody
    public List<ProductDto> getProduct(@RequestParam(value="searchString", required = true) String searchString) {
        List<Product> products = productService.getProductBySearch(searchString);
        return products.stream()
                .map(ProductDto::new)
                .toList();
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.getAllProductList();
        return products.stream()
                .map(ProductDto::new)
                .toList();
    }

    @PostMapping("/addold")
    public ResponseEntity<Product> addProductOld(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

     @PostMapping("/add")
     public ResponseEntity<Product> addProduct(@RequestBody ProductDto product) {
         Supplier supplier = supplierService.getSupplierById(product.getSupplierId());
         Product savedProduct = productService.saveProduct(
                 new Product(0L, supplier, product.getName(), product.getPrice(), product.getType()));
         return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
     }

    @DeleteMapping("/deletebyid")
    @ResponseBody
    public ResponseEntity deleteProduct(@RequestParam(value="id", required=true) Long id) {
        try {
            productService.deleteProductById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Nie można usunąć produktu," +
                    " ponieważ jest on powiązany z innymi rekordami w bazie danych.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

    // TODO: DODAJ METODĘ DO DODAWANIA PRODUKTÓW DO KOSZYKA, check po id czy już jest w koszyku,
    //  jeśli tak to zwiększ ilość
    @PutMapping("/addtocart")
    public ResponseEntity<Product> addProductListToCart(@RequestBody List<ProductDto> productList) {
        productList.forEach(product -> {
            isProductInCart(product);
            Supplier supplier = supplierService.getSupplierById(product.getSupplierId());
            Product savedProduct = productService.saveProduct(
                    new Product(0L, supplier, product.getName(), product.getPrice(), product.getType()));
        });

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // TODO: TU DO TESTÓW, ostatecznie koszyk serwis
    private boolean isProductInCart(ProductDto dto) {
//       OrderFromSupplier item = orderFromSupplierService.getItemById(dto.getId()) // coś takiego
//       if (item != null) {
//
//       }

        return false;
    }

}
