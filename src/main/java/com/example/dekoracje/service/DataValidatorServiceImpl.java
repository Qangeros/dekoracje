package com.example.dekoracje.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class DataValidatorServiceImpl implements DataValidatorService{

    @Override
    public boolean isNameValid(String name) {
        String regex = "^[a-z ,.'-]+$";
        return name != null && Pattern.matches(regex, name) && (2 < name.length() && name.length() < 30);
    }

    @Override
    public boolean isEmailValid(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && Pattern.matches(regex, email);
    }

    @Override
    public boolean isPasswordValid(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";
        return password != null && Pattern.matches(regex, password);
    }

    @Override
    public boolean isPhoneValid(String phone) {
        String regex = "^(\\+48)?[0-9]{9}$";
        return phone != null && Pattern.matches(regex, phone);
    }

    @Override
    public boolean isAddressValid(String address) {
        String regex = "^\\d*[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ ]+\\s\\d+(/\\d+[a-zA-Z]?)?$";
        return address != null && Pattern.matches(regex, address) && (2 < address.length() && address.length() < 100);
    }

    @Override
    public boolean isZipValid(String zip) {
        String regex = "^[0-9]{2}-[0-9]{3}$";
        return zip != null && Pattern.matches(regex, zip);
    }

}
