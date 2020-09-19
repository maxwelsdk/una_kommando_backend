package br.com.kommando.user.repository;

import br.com.kommando.user.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    public Boolean existsByUid(String uid);
}
