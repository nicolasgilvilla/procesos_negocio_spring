package com.procces.business.app.controllers;

import com.procces.business.app.models.User;
import com.procces.business.app.services.UserService;
import com.procces.business.app.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity getUser(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return userService.getUser(id);
    }

    @PostMapping(value = "/user")
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping(value = "/users")
    public ResponseEntity listUser(@RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return userService.listUser();
    }

    @GetMapping(value = "/user/{name}/{lastName}")
    public ResponseEntity getByNameAndLastName(@PathVariable String name, @PathVariable String lastName, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return userService.getByNameAndLastName(name, lastName);
    }

    @GetMapping(value = "/user/lastname/{lastName}")
    public ResponseEntity getByLastName(@PathVariable String lastName, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return userService.getByLastName(lastName);
    }

    @GetMapping(value = "/user/name/{name}")
    public ResponseEntity getByName(@PathVariable String name, @RequestHeader(value = "Authorization") String token) {
        return userService.getByName(name);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity updateUser(@Valid @PathVariable Long id, @RequestBody User user, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return userService.updateUser(id, user);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token invalid");
        }
        return userService.deleteUser(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
