package dev.bacsikm.javaforum.web.post.RO;

import dev.bacsikm.javaforum.web.user.RO.UserResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponse {
    private Long id;
    private String title;
    private String content;
    private UserResponse author;
    private LocalDateTime publishedOn;
    private LocalDateTime updatedOn;
}
