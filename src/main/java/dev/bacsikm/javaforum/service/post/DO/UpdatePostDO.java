package dev.bacsikm.javaforum.service.post.DO;

import lombok.Data;

@Data
public class UpdatePostDO {
    private Long id;
    private String title;
    private String content;
}
