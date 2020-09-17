package br.com.kommando.user.data.services;

import br.com.kommando.user.data.models.User;
import br.com.kommando.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return repository.findAll();
    }

    public User saveUser(User user) {
        return repository.save(user);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUser(String uid) {
        User userExample = new User();
        userExample.setUid(uid);
        return repository.findOne(Example.of(userExample));
    }
}
