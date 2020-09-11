package br.com.kommando.users.api;

import br.com.kommando.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RequestMapping("users")
public class UserController {

    @Autowired
    UserRepository _repository;

    @GetMapping
    ResponseEntity<HashMap<String, Object>> getUsers() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("users", _repository.findAll());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
