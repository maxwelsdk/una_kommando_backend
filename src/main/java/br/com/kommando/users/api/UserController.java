package br.com.kommando.users.api;

import br.com.kommando.users.data.models.User;
import br.com.kommando.users.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RequestMapping("users")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    ResponseEntity<HashMap<String, Object>> getUsers() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("users", service.getUsers());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<HashMap<String, Object>> newUser(@RequestBody User user) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("users", service.user(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
