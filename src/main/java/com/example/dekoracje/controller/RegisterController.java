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

    @GetMapping("")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/add")
    public ResponseEntity<UserTable> registerUser(@RequestBody UserTable userTable) {
        UserTable savedUser = userService.registerUser(userTable);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
