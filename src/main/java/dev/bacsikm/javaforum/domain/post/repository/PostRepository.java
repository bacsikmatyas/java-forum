package dev.bacsikm.javaforum.domain.post.repository;

import dev.bacsikm.javaforum.domain.post.entity.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    boolean existsByIdAndAuthorUsername(long id, String username);
}
