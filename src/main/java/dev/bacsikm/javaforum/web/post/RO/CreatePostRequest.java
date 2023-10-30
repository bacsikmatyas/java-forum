package dev.bacsikm.javaforum.web.post.RO;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String title;
    private String content;
    private String author;
}
