package com.vdudnyk.blogbackend.post;

import com.vdudnyk.blogbackend.tag.TagService;
import com.vdudnyk.blogbackend.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private UserService userService;
    private TagService tagService;
    private PostRepository postRepository;

    public PostService(UserService userService, TagService tagService, PostRepository postRepository) {
        this.userService = userService;
        this.tagService = tagService;
        this.postRepository = postRepository;
    }

    Post addPost(AddPostRequest addPostRequest) {
        Post post = new Post();

        post.setTitle(addPostRequest.getTitle());
        post.setContent(addPostRequest.getContent());
        post.setAuthor(userService.getCurrentUser().orElse(null));
        post.setTags(tagService.saveTagsAndGet(addPostRequest.getTags()));

        return postRepository.save(post);
    }

    Optional<Post> getPostById(String id) {
        return postRepository.findById(Long.valueOf(id));
    }

    List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
