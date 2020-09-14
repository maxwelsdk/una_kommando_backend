package br.com.kommando.user.data.services;

import br.com.kommando.user.data.models.User;
import br.com.kommando.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    public List<User> getUsers() {
        return repository.findAll();
    }

    public User user(User user) {
        System.out.println("Ai baaaaaaa");
        return repository.save(user);
    }
}
