package dev.bacsikm.javaforum.domain.user.repository;

import dev.bacsikm.javaforum.domain.post.entity.Post;
import dev.bacsikm.javaforum.domain.user.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT count(*) FROM user INNER JOIN post ON user.id = post.author_id WHERE user.id = ?1", nativeQuery = true)
    long countPosts(long userId);
}
