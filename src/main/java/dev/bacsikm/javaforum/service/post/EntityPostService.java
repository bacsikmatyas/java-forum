package dev.bacsikm.javaforum.service.post;

import dev.bacsikm.javaforum.domain.post.entity.Post;
import dev.bacsikm.javaforum.domain.post.repository.PostRepository;
import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.service.post.transformer.PostDOTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityPostService implements PostService {

    private final PostRepository postRepository;
    private final PostDOTransformer postTransformer;
    Logger logger = LoggerFactory.getLogger(EntityPostService.class);

    @Autowired
    public EntityPostService(PostRepository postRepository, PostDOTransformer postTransformer) {
        this.postRepository = postRepository;
        this.postTransformer = postTransformer;
    }

    @Override
    public List<PostDO> getAllPost() {
        Iterable<Post> postsIterable = postRepository.findAll();
        logger.info("Found {} posts", postsIterable.spliterator().getExactSizeIfKnown());
        return postTransformer.fromIterable(postsIterable);
    }

    @Override
    public PostDO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        logger.info("Found post with id {}", id);
        return postTransformer.from(post);
    }
}
