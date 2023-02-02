package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.Address;
import com.example.dekoracje.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private DataValidatorService dataValidatorService;

    @Override
    public List<Address> getAllAddressList() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Address> getAddressBySearch(String searchString) {
        return addressRepository.findAddressBySearch(searchString).orElseThrow();
    }

    @Override
    public Address saveAddress(Address address) {
        if (!dataValidatorService.isAddressValid(address.getStreet())) {
            throw new IllegalArgumentException("Adres niepoprawny");
        }
        if (!dataValidatorService.isZipValid(address.getPostalCode())) {
            throw new IllegalArgumentException("Kod pocztowy niepoprawny");
        }
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(Long id) {
        addressRepository.deleteById(id);
    }
}
