package com.procces.business.app.services;

import com.procces.business.app.models.User;
import com.procces.business.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        try {
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> listUser() {
        List<User> listUsers = userRepository.findAll();
        if (!listUsers.isEmpty()) {
            return new ResponseEntity<>(listUsers, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/user/{name}/{lastName}")
    public ResponseEntity<List<User>> getByNameAndLastName(@PathVariable String name, @PathVariable String lastName) {
        List<User> listUsersForNameAndLastName = userRepository.findAllByNameAndLastName(name, lastName);
        if (!listUsersForNameAndLastName.isEmpty()) {
            return new ResponseEntity<>(listUsersForNameAndLastName, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/user/lastname/{lastName}")
    public ResponseEntity<List<User>> getByLastName(@PathVariable String lastName) {
        List<User> listUsersForLastName = userRepository.findAllByLastName(lastName);
        if (!listUsersForLastName.isEmpty()) {
            return new ResponseEntity<>(listUsersForLastName, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/user/name/{name}")
    public ResponseEntity<List<User>> getByName(@PathVariable String name) {
        List<User> listUsersForName = userRepository.findAllByName(name);
        if (!listUsersForName.isEmpty()) {
            return new ResponseEntity<>(listUsersForName, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userFind = userRepository.findById(id);
        try {
            if (userFind.isPresent()) {
                User userPresent = userFind.get();
                userPresent.setName(user.getName());
                userPresent.setLastName(user.getLastName());
                userPresent.setDocument(user.getDocument());
                userPresent.setAddress(user.getAddress());
                userPresent.setDateOfBirth(user.getDateOfBirth());
                userPresent.setNumberPhone(user.getNumberPhone());
                userRepository.save(userPresent);
                return new ResponseEntity<>(userPresent, HttpStatus.OK);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
