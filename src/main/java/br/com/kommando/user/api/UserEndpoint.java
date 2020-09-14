package br.com.kommando.user.api;

import br.com.kommando.user.data.models.User;
import br.com.kommando.user.data.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("users")
public class UserEndpoint {

    @Autowired
    UserService service;

    @GetMapping
    ResponseEntity<HashMap<String, Object>> getUsers() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("users", service.getUsers());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<HashMap<String, Object>> newUser(@RequestBody User user) throws Exception {
        HashMap<String, Object> response = new HashMap<>();
        if (user.getUid().isBlank()) {
            response.put("error", new Exception("uid falhou"));
            return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
        }
        response.put("users", service.user(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
