package com.example.dekoracje.repository;

import com.example.dekoracje.model.dto.OrdersFromCustomerDto;
import com.example.dekoracje.model.entity.OrdersFromCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersFromCustomerRepository extends JpaRepository<OrdersFromCustomer, Long> {

    // TODO check czy to przejdzie o.customer.pole
    @Query("SELECT o FROM OrdersFromCustomer o WHERE " +
            "   o.customer.name LIKE '%' ||" + " :searchString || '%' " +
            "OR o.customer.surname LIKE '%' || :searchString || '%' " +
            "OR o.document.name LIKE '%' || :searchString || '%' ")
    Optional<List<OrdersFromCustomer>> findOrdersFromCustomerBySearch(String searchString);

    // TODO dopisać sqla na update po DTOsie
    @Modifying
    @Query("UPDATE OrdersFromCustomer s " +
            "SET s.price = :#{#dto.price}, " +
            "s.completed = :#{#dto.completed} WHERE s.id = :#{#dto.id}")
    void updateOrdersFromCustomer(@Param("dto") OrdersFromCustomerDto dto);
}