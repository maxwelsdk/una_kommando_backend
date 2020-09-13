package br.com.kommando.user.repository;

import br.com.kommando.user.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
