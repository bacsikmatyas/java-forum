package dev.bacsikm.javaforum.web.post.controller;

import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.service.post.PostService;
import dev.bacsikm.javaforum.web.post.RO.CreatePostRequest;
import dev.bacsikm.javaforum.web.post.RO.PostResponse;
import dev.bacsikm.javaforum.web.post.RO.UpdatePostRequest;
import dev.bacsikm.javaforum.web.post.transformer.PostRequestTransformer;
import dev.bacsikm.javaforum.web.post.transformer.PostResponseTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;
    private final PostResponseTransformer postResponseTransformer;
    private final PostRequestTransformer postRequestTransformer;
    Logger logger = LoggerFactory.getLogger(PostController.class);


    @Autowired
    public PostController(PostService postService, PostResponseTransformer postResponseTransformer, PostRequestTransformer postRequestTransformer) {
        this.postService = postService;
        this.postResponseTransformer = postResponseTransformer;
        this.postRequestTransformer = postRequestTransformer;
    }

    @GetMapping("/get")
    public List<PostResponse> getAllPosts() {
        logger.info("Getting all posts");
        List<PostDO> allPosts = postService.getAllPost();
        return postResponseTransformer.fromList(allPosts);
    }

    @GetMapping("/get/{id}")
    public PostResponse getPostById(@PathVariable long id) {
        logger.info("Getting post with id {}", id);
        PostDO postById = postService.getPostById(id);
        return postResponseTransformer.from(postById);
    }

    @PostMapping("/create")
    public PostResponse createPost(Principal principal, @RequestBody CreatePostRequest createPostRequest) {
        logger.info("Creating post");
        postService.checkAuthorForNew(createPostRequest.getAuthor(), principal.getName());
        PostDO postDO = postRequestTransformer.to(createPostRequest);
        return postResponseTransformer.from(postService.createPost(postDO));
    }

    @PutMapping("/update")
    public PostResponse updatePost(Principal principal, @RequestBody UpdatePostRequest updatePostRequest) {
        logger.info("Updating post");
        postService.checkAuthorForExisting(updatePostRequest.getId(), principal.getName());
        PostDO postDO = postRequestTransformer.to(updatePostRequest);
        return postResponseTransformer.from(postService.updatePost(postDO));
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(Principal principal, @PathVariable long id) {
        logger.info("Deleting post with id {}", id);
        postService.checkAuthorForExisting(id, principal.getName());
        postService.deletePost(id);
    }
}
