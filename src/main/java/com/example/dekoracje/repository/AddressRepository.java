package com.example.dekoracje.repository;

import com.example.dekoracje.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.street LIKE '%' ||" +
            " :searchString || '%' OR a.city LIKE '%' || :searchString ||" +
            " '%' OR a.postalCode LIKE '%' || :searchString || '%'")
    Optional<List<Address>> findAddressBySearch(String searchString);

}