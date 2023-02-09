package com.example.dekoracje.service;

import com.example.dekoracje.model.entity.UserTable;
import com.example.dekoracje.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataValidatorService dataValidatorService;

    @Override
    public UserTable registerUser(UserTable userTable) {
        String errorMessage = validateUser(userTable);
        if (!(errorMessage == null)) {
            throw new RuntimeException(errorMessage);
        }
        return userRepository.save(userTable);
    }

    private String validateUser(UserTable userTable) {
        if (!dataValidatorService.isEmailValid(userTable.getEmail())
                && !dataValidatorService.isPasswordValid(userTable.getPassword())) {
            return "Niepoprawny adres e-mail lub hasło";
        }

        if (userRepository.existsByUsername(userTable.getUsername())) {
            return "Użytkownik o tej nazwie już istnieje";
        }

        if (userRepository.existsByEmail(userTable.getEmail())) {
            return "Użytkownik o tym adresie e-mail już istnieje";
        }
        return null;
    }
}
