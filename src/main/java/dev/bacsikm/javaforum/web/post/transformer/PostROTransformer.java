package dev.bacsikm.javaforum.web.post.transformer;

import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.web.post.RO.PostRO;
import dev.bacsikm.javaforum.web.user.transformer.UserROTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostROTransformer {

    private final UserROTransformer userROTransformer;

    @Autowired
    public PostROTransformer(UserROTransformer userROTransformer) {
        this.userROTransformer = userROTransformer;
    }

    public PostDO to(PostRO postRO) {
        PostDO postDO = new PostDO();

        postDO.setId(postRO.getId());
        postDO.setTitle(postRO.getTitle());
        postDO.setContent(postRO.getContent());
        postDO.setAuthor(userROTransformer.to(postRO.getAuthor()));
        postDO.setPublishedOn(postRO.getPublishedOn());
        postDO.setUpdatedOn(postRO.getUpdatedOn());

        return postDO;
    }

    public PostRO from(PostDO postDO) {
        PostRO postRO = new PostRO();

        postRO.setId(postDO.getId());
        postRO.setTitle(postDO.getTitle());
        postRO.setContent(postDO.getContent());
        postRO.setAuthor(userROTransformer.from(postDO.getAuthor()));
        postRO.setPublishedOn(postDO.getPublishedOn());
        postRO.setUpdatedOn(postDO.getUpdatedOn());

        return postRO;
    }

    public List<PostRO> fromList(List<PostDO> postDOs) {
        List<PostRO> postROs = new ArrayList<>();
        postDOs.forEach((post) -> postROs.add(from(post)));
        return postROs;
    }
}
