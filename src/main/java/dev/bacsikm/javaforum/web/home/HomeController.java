package dev.bacsikm.javaforum.web.home;

import dev.bacsikm.javaforum.service.post.DO.PostDO;
import dev.bacsikm.javaforum.service.post.PostService;
import dev.bacsikm.javaforum.web.post.RO.PostRO;
import dev.bacsikm.javaforum.web.post.transformer.PostROTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class HomeController {

    private final PostService postService;
    private final PostROTransformer postROTransformer;

    @Autowired
    public HomeController(PostService postService, PostROTransformer postROTransformer) {
        this.postService = postService;
        this.postROTransformer = postROTransformer;
    }

    @GetMapping("/")
    public Map<String, Object> greeting(Principal principal){
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @GetMapping("/get")
    public List<PostRO> getAllPosts() {
        List<PostDO> allPosts = postService.getAllPost();
        return postROTransformer.fromList(allPosts);
    }
}
