package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.OrdersFromSupplier;
import com.example.dekoracje.repository.OrdersFromSupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class OrdersFromSupplierServiceImpl implements OrdersFromSupplierService {

    @Autowired
    private OrdersFromSupplierRepository ordersFromSupplierRepository;

    @Override
    public OrdersFromSupplier saveOrdersFromSupplier(OrdersFromSupplier ordersFromSupplier) {
        return ordersFromSupplierRepository.save(ordersFromSupplier);
    }

    @Override
    public String generateOrderNumber() {
        long nextNumber = ordersFromSupplierRepository.count() + 1;
        return "ZAMOD/" + nextNumber + "/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("MM/yy"));
    }
}
