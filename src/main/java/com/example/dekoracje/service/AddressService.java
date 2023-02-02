package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Address;

import java.util.List;

public interface AddressService {

    List<Address> getAllAddressList();
    Address getAddressById(Long id);
    List<Address> getAddressBySearch(String searchString);
    Address saveAddress(Address address);
    void deleteAddressById(Long id);
}
