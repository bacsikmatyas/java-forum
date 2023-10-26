package dev.bacsikm.javaforum.web.post.controller;

import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.service.post.PostService;
import dev.bacsikm.javaforum.web.post.RO.PostRO;
import dev.bacsikm.javaforum.web.post.transformer.PostROTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final PostROTransformer postROTransformer;
    Logger logger = LoggerFactory.getLogger(PostController.class);


    @Autowired
    public PostController(PostService postService, PostROTransformer postROTransformer) {
        this.postService = postService;
        this.postROTransformer = postROTransformer;
    }

    @GetMapping("/get")
    public List<PostRO> getAllPosts() {
        logger.info("Getting all posts");
        List<PostDO> allPosts = postService.getAllPost();
        return postROTransformer.fromList(allPosts);
    }

    @GetMapping("/get/{id}")
    public PostRO getPostById(@PathVariable Long id) {
        logger.info("Getting post with id {}", id);
        PostDO postById = postService.getPostById(id);
        return postROTransformer.from(postById);
    }

}
