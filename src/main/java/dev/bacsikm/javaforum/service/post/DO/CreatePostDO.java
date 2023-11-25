package dev.bacsikm.javaforum.service.post.DO;

import lombok.Data;

@Data
public class CreatePostDO {
    private String title;
    private String content;
    private String author;
}
