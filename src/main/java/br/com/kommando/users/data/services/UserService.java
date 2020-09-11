package br.com.kommando.users.data.services;

import br.com.kommando.users.data.models.User;
import br.com.kommando.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User user(User user) {
        return repository.save(user);
    }
}
