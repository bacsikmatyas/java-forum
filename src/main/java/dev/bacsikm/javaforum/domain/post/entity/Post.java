package dev.bacsikm.javaforum.domain.post.entity;

import dev.bacsikm.javaforum.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private LocalDateTime publishedOn;
    private LocalDateTime updatedOn;

    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    public Post() {
    }

    public Post(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.publishedOn = LocalDateTime.now();
        this.author = author;
    }
}
