package com.example.dekoracje.controller;

import com.example.dekoracje.model.entity.UserTable;
import com.example.dekoracje.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegisterController {

    @Autowired
    private UserService userService;

    private static final long SUPPLIER_TYPE_ID = 2L;
    private static final long CUSTOMER_TYPE_ID = 3L;

    @GetMapping("")
    public String showRegisterPage() {
        return "register";
    }

//    @PostMapping("/add")
//    public ResponseEntity<UserTable> registerUser(@RequestBody UserTable userTable) {
//        if (userTable.getUserType().getName().equals("customer")){
//            userTable.getUserType().setId(CUSTOMER_TYPE_ID);
//        }
//        else if (userTable.getUserType().getName().equals("supplier")) {
//            userTable.getUserType().setId(SUPPLIER_TYPE_ID);
//        }
//        UserTable savedUser = userService.registerUser(userTable);
//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//    }
}
