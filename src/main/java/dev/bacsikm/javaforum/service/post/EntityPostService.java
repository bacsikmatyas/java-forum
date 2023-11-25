package dev.bacsikm.javaforum.service.post;

import dev.bacsikm.javaforum.domain.post.entity.Post;
import dev.bacsikm.javaforum.domain.post.repository.PostRepository;
import dev.bacsikm.javaforum.domain.user.repository.UserRepository;
import dev.bacsikm.javaforum.service.post.DO.CreatePostDO;
import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.service.post.DO.UpdatePostDO;
import dev.bacsikm.javaforum.service.post.exception.AuthorMismatchException;
import dev.bacsikm.javaforum.service.post.exception.PostNotFoundException;
import dev.bacsikm.javaforum.service.post.transformer.PostDOTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntityPostService implements PostService {

    private final PostRepository postRepository;
    private final PostDOTransformer postTransformer;
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(EntityPostService.class);

    @Autowired
    public EntityPostService(PostRepository postRepository, PostDOTransformer postTransformer, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.postTransformer = postTransformer;
        this.userRepository = userRepository;
    }

    @Override
    public List<PostDO> getAllPost() {
        Iterable<Post> postsIterable = postRepository.findAll();
        logger.info("Found {} posts", postsIterable.spliterator().getExactSizeIfKnown());
        return postTransformer.fromIterable(postsIterable);
    }

    @Override
    public PostDO getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
        logger.info("Found post with id {}", id);
        return postTransformer.from(post);
    }

    @Override
    public PostDO createPost(CreatePostDO createPostDO) {
        Post post = postTransformer.to(createPostDO);
        userRepository.findByUsername(post.getAuthor().getUsername()).ifPresent(post::setAuthor);
        Post savedPost = postRepository.save(post);
        logger.info("Created post with id {}", savedPost.getId());
        return postTransformer.from(savedPost);
    }

    @Override
    public PostDO updatePost(UpdatePostDO updatePostDO) {
        checkIfPostExists(updatePostDO.getId());
        Post post = postRepository.findById(updatePostDO.getId()).get();
        post.setTitle(updatePostDO.getTitle());
        post.setContent(updatePostDO.getContent());
        post.setUpdatedOn(LocalDateTime.now());
        Post updatedPost = postRepository.save(post);
        logger.info("Updated post with id {}", updatedPost.getId());
        return postTransformer.from(updatedPost);
    }

    @Override
    public void checkAuthorForNew(String username, String principal) {
        logger.info("Checking if user {} is the author of new post", username);
        if (!username.equals(principal)) {
            throw new AuthorMismatchException(principal);
        }
    }

    @Override
    public void checkAuthorForExisting(long id, String author) {
        logger.info("Checking if user {} is the author of post with id {}", author, id);
        if (!postRepository.existsByIdAndAuthorUsername(id, author)) {
            throw new AuthorMismatchException(id, author);
        }
    }

    @Override
    public void deletePost(long id) {
        checkIfPostExists(id);
        postRepository.deleteById(id);
        logger.info("Deleted post with id {}", id);
    }

    private void checkIfPostExists(long id) {
        logger.info("Checking if post with id {} exists", id);
        if (!postRepository.existsById(id)) {
            throw new PostNotFoundException(id);
        }
    }
}
