package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

    @Query(value = "SELECT pio FROM ProductInOrder pio WHERE pio.ordersFromSupplier IS NULL")
    List<ProductInOrder> getAllCartProducts();

    @Modifying
    @Query(value = "UPDATE ProductInOrder pio SET pio.amount = :amount WHERE pio.id = :id")
    void updateProductInOrder(@Param("id") Long id, @Param("amount") Integer amount);


    @Modifying
    @Query(value = "UPDATE ProductInOrder pio SET pio.product = :#{#productInOrder.product} WHERE pio.id = :#{#productInOrder.id}")
    void updateProductInOrder(ProductInOrder productInOrder);
}