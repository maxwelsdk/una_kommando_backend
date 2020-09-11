package br.com.kommando.users.repository;

import br.com.kommando.users.data.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
