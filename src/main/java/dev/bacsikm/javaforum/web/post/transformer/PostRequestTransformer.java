package dev.bacsikm.javaforum.web.post.transformer;

import dev.bacsikm.javaforum.service.post.DO.CreatePostDO;
import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.service.post.DO.UpdatePostDO;
import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.web.post.RO.CreatePostRequest;
import dev.bacsikm.javaforum.web.post.RO.UpdatePostRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostRequestTransformer {
    public CreatePostDO to(CreatePostRequest createPostRequest) {
        CreatePostDO createPostDO = new CreatePostDO();

        createPostDO.setTitle(createPostRequest.getTitle());
        createPostDO.setContent(createPostRequest.getContent());
        createPostDO.setAuthor(createPostRequest.getAuthor());

        return createPostDO;
    }

    public UpdatePostDO to(UpdatePostRequest updatePostRequest) {
        UpdatePostDO updatePostDO = new UpdatePostDO();

        updatePostDO.setId(updatePostRequest.getId());
        updatePostDO.setTitle(updatePostRequest.getTitle());
        updatePostDO.setContent(updatePostRequest.getContent());

        return updatePostDO;
    }
}
