package dev.bacsikm.javaforum.service.post.DO;

import dev.bacsikm.javaforum.service.user.DO.UserDO;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDO { //TODO: create different DOs for different purposes
    private Long id;
    private String title;
    private String content;
    private UserDO author;
    private LocalDateTime publishedOn;
    private LocalDateTime updatedOn;
}
