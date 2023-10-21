package dev.bacsikm.javaforum.domain.user.entity;

import dev.bacsikm.javaforum.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, length = 60)
    private String username;
    @Column(nullable = false, length = 60)
    private String password;
    @Column(nullable = false, columnDefinition = "varchar(255) default 'ROLE_USER'")
    private String roles;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts;

    public User() {
    }

    public User(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
