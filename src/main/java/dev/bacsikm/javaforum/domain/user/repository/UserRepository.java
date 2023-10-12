package dev.bacsikm.javaforum.domain.user.repository;

import dev.bacsikm.javaforum.domain.user.entity.User;
import dev.bacsikm.javaforum.domain.user.projection.UserInfoProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT count(*) FROM user INNER JOIN post ON user.id = post.author_id WHERE user.id = ?1", nativeQuery = true)
    long countPosts(long userId);

    @Query("SELECT new dev.bacsikm.javaforum.domain.user.projection.UserInfoProjection(u.id, u.roles, u.username, COUNT(p.id)) FROM User u LEFT JOIN u.posts p WHERE u.id = ?1 GROUP BY u.id, u.roles, u.username")
    UserInfoProjection findUserInfoById(long id);
}
