package dev.bacsikm.javaforum.service.post;

import dev.bacsikm.javaforum.service.post.DO.PostDO;

import java.util.List;

public interface PostService {
    List<PostDO> getAllPost();

    PostDO getPostById(Long id);

    PostDO createPost(PostDO postDO);

    PostDO updatePost(PostDO postDO);

    void checkAuthorForNew(String username, String principal);

    void checkAuthorForExisting(long id, String author);

    void deletePost(long id);
}
