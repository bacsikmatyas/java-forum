package dev.bacsikm.javaforum.service.post;

import dev.bacsikm.javaforum.domain.post.entity.Post;
import dev.bacsikm.javaforum.domain.post.repository.PostRepository;
import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.service.post.transformer.PostDOTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityPostService implements PostService {

    private final PostRepository postRepository;
    private final PostDOTransformer postTransformer;

    @Autowired
    public EntityPostService(PostRepository postRepository, PostDOTransformer postTransformer) {
        this.postRepository = postRepository;
        this.postTransformer = postTransformer;
    }

    @Override
    public List<PostDO> getAllPost() {
        Iterable<Post> postsIterable = postRepository.findAll();
        return postTransformer.fromIterable(postsIterable);
    }

    @Override
    public PostDO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow();
        return postTransformer.from(post);
    }
}
