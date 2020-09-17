package br.com.kommando.user.api;

import br.com.kommando.user.data.models.User;
import br.com.kommando.user.data.services.UserService;
import br.com.kommando.user.error.InvalidUidException;
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

    @GetMapping(path = "/{uid}")
    ResponseEntity<HashMap<String, Object>> getUserByUid(@PathVariable String uid) {
        HashMap<String, Object> response = new HashMap<>();
        response.put("user", service.getUser(uid));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<HashMap<String, Object>> newUser(@RequestBody User user) {
        HashMap<String, Object> response = new HashMap<>();
        if (user.getUid() == null) throw new InvalidUidException("Indentificação única de usuário inválida");
        response.put("users", service.saveUser(user));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //TODO: Endpoint para update user;
}
