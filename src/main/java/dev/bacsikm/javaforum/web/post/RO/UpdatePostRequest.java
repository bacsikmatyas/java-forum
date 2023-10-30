package dev.bacsikm.javaforum.web.post.RO;

import lombok.Data;

@Data
public class UpdatePostRequest {
    private Long id;
    private String title;
    private String content;
}
