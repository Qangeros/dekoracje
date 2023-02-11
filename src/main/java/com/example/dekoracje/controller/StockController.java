package com.example.dekoracje.controller;

import com.example.dekoracje.controller.util.ErrorResponse;
import com.example.dekoracje.model.dto.StockDto;
import com.example.dekoracje.model.entity.Stock;
import com.example.dekoracje.service.StockService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("")
    public String showStockPage() {
        return "stock";
    }

    @GetMapping("/getbyid")
    @ResponseBody
    public StockDto getStock(@RequestParam(value="id", required = true) Long id) {
        Stock stock = stockService.getStockById(id);
        return new StockDto(stock);
    }

    @GetMapping("/getbystring")
    @ResponseBody
    public List<StockDto> getStock(@RequestParam(value="searchString", required = true) String searchString) {
        List<Stock> stocks = stockService.getStockBySearch(searchString);
        return stocks.stream()
                .map(StockDto::new)
                .toList();
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<StockDto> getAllStocks() {
        List<Stock> stocks = stockService.getAllStockList();
        return stocks.stream()
                .map(StockDto::new)
                .toList();
    }

    @PostMapping("/add")
    public ResponseEntity<Stock> addStock(@RequestBody Stock stock) {
        Stock savedStock = stockService.saveStock(stock);
        return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid")
    @ResponseBody
    public ResponseEntity<ErrorResponse> deleteStock(@RequestParam(value="id", required = true) Long id) {
        try {
            stockService.deleteStockById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Nie można usunąć stanu magazynowego," +
                    " ponieważ jest on powiązany z innymi rekordami w bazie danych.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

//    @PutMapping("/update") //TODO
//    public ResponseEntity<ErrorResponse> updateStockAmount(@RequestParam(value="id", required = true) Long id,
//                                                           @RequestParam(value="amount", required = true) Integer amount) {
//        try {
//            stockService.updateStockAmount(id, amount);
//            return new ResponseEntity<>(HttpStatus.OK);
//        } catch (DataIntegrityViolationException e) {
//            ErrorResponse error = new ErrorResponse("Wystąpił błąd podczas aktualizacji stanu magazynowego.");
//            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
//        }
//    }

    @Transactional
    @PutMapping("/updatelist")
    public ResponseEntity<ErrorResponse> updateStockAmountList(@RequestBody List<StockDto> stockDtos) {
        try {
            stockDtos.forEach(s -> stockService.updateStockAmount(s.getId(), s.getAmount()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Wystąpił błąd podczas aktualizacji stanu magazynowego.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }
}
