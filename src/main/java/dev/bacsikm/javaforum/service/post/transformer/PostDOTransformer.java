package dev.bacsikm.javaforum.service.post.transformer;

import dev.bacsikm.javaforum.domain.post.entity.Post;
import dev.bacsikm.javaforum.service.post.DO.PostDO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostDOTransformer {

    public Post to(PostDO postDO) {
        Post post = new Post();

        post.setId(postDO.getId());
        post.setTitle(postDO.getTitle());
        post.setContent(post.getContent());
        post.setAuthor(post.getAuthor());
        post.setPublishedOn(postDO.getPublishedOn());
        post.setUpdatedOn(postDO.getUpdatedOn());

        return post;
    }

    public PostDO from(Post post) {
        PostDO postDO = new PostDO();

        postDO.setId(post.getId());
        postDO.setTitle(post.getTitle());
        postDO.setContent(post.getContent());
        postDO.setAuthor(post.getAuthor());
        postDO.setPublishedOn(post.getPublishedOn());
        postDO.setUpdatedOn(post.getUpdatedOn());

        return postDO;
    }

    public List<PostDO> fromIterable(Iterable<Post> posts) {
        List<PostDO> postDOs = new ArrayList<>();
        posts.forEach((post) -> postDOs.add(from(post)));
        return postDOs;
    }
}
