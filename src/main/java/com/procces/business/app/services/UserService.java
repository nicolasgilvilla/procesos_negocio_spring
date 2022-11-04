package com.procces.business.app.services;

import com.procces.business.app.models.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<User> getUser(Long id);

    ResponseEntity<User> createUser(User user);

    ResponseEntity<List<User>> listUser();

    ResponseEntity<List<User>> getByNameAndLastName(String name, String lastName);

    ResponseEntity<List<User>> getByName(String name);

    ResponseEntity<List<User>> getByLastName(String lastName);


    ResponseEntity<User> updateUser(Long id, User user);


    ResponseEntity<User> deleteUser(Long id);

    ResponseEntity login(String email, String password);

}

