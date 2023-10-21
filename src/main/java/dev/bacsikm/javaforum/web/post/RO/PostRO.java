package dev.bacsikm.javaforum.web.post.RO;

import dev.bacsikm.javaforum.web.user.RO.UserRO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostRO {
    private Long id;
    private String title;
    private String content;
    private UserRO author;
    private LocalDateTime publishedOn;
    private LocalDateTime updatedOn;
}
