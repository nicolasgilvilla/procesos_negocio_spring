package com.procces.business.app.controllers;

import com.procces.business.app.models.User;
import com.procces.business.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "auth/login")
    public ResponseEntity login(@RequestBody User user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

}
