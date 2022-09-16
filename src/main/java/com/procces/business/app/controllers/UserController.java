package com.procces.business.app.controllers;

import com.procces.business.app.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
public class UserController {

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable Long id) {
        User user = new User();
        user.setName("Nicolas");
        user.setLastName("Gil");
        user.setNumberPhone("3106336353");
        user.setAddress("Barrio Peñon");
        user.setDocument("123456");
        user.setDateOfBirth(new Date("11/9/2021"));
        return user;
    }
}
