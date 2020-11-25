package br.com.kommando.user.data.services;

import br.com.kommando.exception.error.DataFoundException;
import br.com.kommando.exception.error.DataNotFoundException;
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
    public User getUser(String uid) {
        User foundUser = repository.findByUid(uid);
        if (foundUser == null) throw new DataNotFoundException("Usuário não encontrado");
        return foundUser;
    }

    public void deleteById(String id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
        } else {
            throw new DataNotFoundException("Usuário não encontrado");
        }
    }

    public User updateUser(User user) {
        if (repository.findById(user.getId()).isPresent()) {
            deleteById(user.getId());
            return saveUser(user);
        } else {
            throw new DataFoundException("Usuário não encontrado!");
        }
    }
}
