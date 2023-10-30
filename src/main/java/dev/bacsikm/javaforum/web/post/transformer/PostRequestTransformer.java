package dev.bacsikm.javaforum.web.post.transformer;

import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.service.user.DO.UserDO;
import dev.bacsikm.javaforum.web.post.RO.CreatePostRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PostRequestTransformer {
    public PostDO to(CreatePostRequest createPostRequest) {
        PostDO postDO = new PostDO();

        postDO.setTitle(createPostRequest.getTitle());
        postDO.setContent(createPostRequest.getContent());
        UserDO author = new UserDO();
        author.setUsername(createPostRequest.getAuthor());
        postDO.setAuthor(author);
        postDO.setPublishedOn(LocalDateTime.now());
        postDO.setUpdatedOn(LocalDateTime.now());

        return postDO;
    }
}
