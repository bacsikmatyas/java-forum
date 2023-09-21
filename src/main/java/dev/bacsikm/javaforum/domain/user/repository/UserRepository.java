package dev.bacsikm.javaforum.domain.user.repository;

import dev.bacsikm.javaforum.domain.user.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
