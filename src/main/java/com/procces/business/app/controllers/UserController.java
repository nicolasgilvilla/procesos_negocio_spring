package com.procces.business.app.controllers;

import com.procces.business.app.models.User;
import com.procces.business.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user/{id}")
    public Optional<User> getUser(@PathVariable Long id) {
        return userRepository.findById(id);
    }

    @PostMapping(value = "/user")
    public User createUser(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping(value = "/users")
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/user/{name}/{lastName}")
    public List<User> getByNameAndLastName(@PathVariable String name, @PathVariable String lastName) {
        return userRepository.findAllByNameAndLastName(name, lastName);
    }

    @GetMapping(value = "/user/lastname/{lastName}")
    public List<User> getByLastName(@PathVariable String lastName) {
        return userRepository.findAllByLastName(lastName);
    }

    @GetMapping(value = "/user/name/{name}")
    public List<User> getByName(@PathVariable String name) {
        return userRepository.findAllByName(name);
    }

    @PutMapping(value = "/user/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        User _user = userRepository.findById(id).get();
        try {
            _user.setName(user.getName());
            _user.setLastName(user.getLastName());
            _user.setDocument(user.getDocument());
            _user.setAddress(user.getAddress());
            _user.setDateOfBirth(user.getDateOfBirth());
            _user.setNumberPhone(user.getNumberPhone());
            userRepository.save(_user);
            return _user;
        } catch (Exception e) {
            return null;
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public User deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).get();
        try {
            userRepository.delete(user);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

}
