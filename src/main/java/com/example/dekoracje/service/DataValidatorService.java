package com.example.dekoracje.service;

public interface DataValidatorService {
    boolean isNameValid(String name);
    boolean isEmailValid(String email);
    boolean isPasswordValid(String password);
    boolean isPhoneValid(String phone);
    boolean isAddressValid(String address);
    boolean isZipValid(String zip);
}
