package dev.bacsikm.javaforum.service.post;

import dev.bacsikm.javaforum.service.post.DO.PostDO;

import java.util.List;

public interface PostService {
    List<PostDO> getAllPost();

    PostDO getPostById(Long id);
}