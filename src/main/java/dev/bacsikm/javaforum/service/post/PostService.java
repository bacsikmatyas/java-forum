package dev.bacsikm.javaforum.service.post;

import dev.bacsikm.javaforum.service.post.DO.CreatePostDO;
import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.service.post.DO.UpdatePostDO;

import java.util.List;

public interface PostService {
    List<PostDO> getAllPost();

    PostDO getPostById(long id);

    PostDO createPost(CreatePostDO createPostDO);

    PostDO updatePost(UpdatePostDO updatePostDO);

    void checkAuthorForNew(String username, String principal);

    void checkAuthorForExisting(long id, String author);

    void deletePost(long id);
}
