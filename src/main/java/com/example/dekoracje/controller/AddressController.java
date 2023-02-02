package com.example.dekoracje.controller;

import com.example.dekoracje.controller.util.ErrorResponse;
import com.example.dekoracje.model.dto.AddressDto;
import com.example.dekoracje.model.entity.Address;
import com.example.dekoracje.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/address")
public class AddressController{

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public String showAddressPage() {
        return "address";
    }

    @GetMapping("/getbyid")
    @ResponseBody
    public AddressDto getAddress(@RequestParam(value="id", required = true) Long id) {
        Address address = addressService.getAddressById(id);
        return new AddressDto(address);
    }

    @GetMapping("/getbystring")
    @ResponseBody
    public List<AddressDto> getAddress(@RequestParam(value="searchString", required = true) String searchString) {
        List<Address> addresses = addressService.getAddressBySearch(searchString);
        return addresses.stream()
                .map(AddressDto::new)
                .toList();
    }

    @GetMapping("/getall")
    @ResponseBody
    public List<AddressDto> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddressList();
        return addresses.stream()
                .map(AddressDto::new)
                .toList();
    }

    @PostMapping("/add")
    public ResponseEntity<Address> addAddress(@RequestBody Address address) {
        Address savedAddress = addressService.saveAddress(address);
        return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletebyid")
    @ResponseBody
    public ResponseEntity<ErrorResponse> deleteAddress(@RequestParam(value="id", required=true) Long id) {
        try {
            addressService.deleteAddressById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException e) {
            ErrorResponse error = new ErrorResponse("Nie można usunąć adresu," +
                    " ponieważ jest on powiązany z innymi rekordami w bazie danych.");
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
    }

}
