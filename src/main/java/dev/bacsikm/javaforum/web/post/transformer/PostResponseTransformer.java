package dev.bacsikm.javaforum.web.post.transformer;

import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.web.post.RO.PostResponse;
import dev.bacsikm.javaforum.web.user.transformer.UserROTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostResponseTransformer {

    private final UserROTransformer userROTransformer;

    @Autowired
    public PostResponseTransformer(UserROTransformer userROTransformer) {
        this.userROTransformer = userROTransformer;
    }

    public PostDO to(PostResponse postResponse) {
        PostDO postDO = new PostDO();

        postDO.setId(postResponse.getId());
        postDO.setTitle(postResponse.getTitle());
        postDO.setContent(postResponse.getContent());
        postDO.setAuthor(userROTransformer.to(postResponse.getAuthor()));
        postDO.setPublishedOn(postResponse.getPublishedOn());
        postDO.setUpdatedOn(postResponse.getUpdatedOn());

        return postDO;
    }

    public PostResponse from(PostDO postDO) {
        PostResponse postResponse = new PostResponse();

        postResponse.setId(postDO.getId());
        postResponse.setTitle(postDO.getTitle());
        postResponse.setContent(postDO.getContent());
        postResponse.setAuthor(userROTransformer.from(postDO.getAuthor()));
        postResponse.setPublishedOn(postDO.getPublishedOn());
        postResponse.setUpdatedOn(postDO.getUpdatedOn());

        return postResponse;
    }

    public List<PostResponse> fromList(List<PostDO> postDOs) {
        List<PostResponse> postResponses = new ArrayList<>();
        postDOs.forEach((post) -> postResponses.add(from(post)));
        return postResponses;
    }
}
