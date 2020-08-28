package com.redd4ford.insteadit.controller;

import com.redd4ford.insteadit.dto.PostRequest;
import com.redd4ford.insteadit.dto.PostResponse;
import com.redd4ford.insteadit.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/api/posts")
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping
  public ResponseEntity<List<PostResponse>> getAllPosts() {
    return status(HttpStatus.OK).body(postService.getAllPosts());
  }

  @GetMapping("/{id}")
  public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
    return status(HttpStatus.OK).body(postService.getPost(id));
  }

  @GetMapping("by-thread/{id}")
  public ResponseEntity<List<PostResponse>> getPostsByThread(Long id) {
    return status(HttpStatus.OK).body(postService.getPostsByThread(id));
  }

  @GetMapping("by-user/{username}")
  public ResponseEntity<List<PostResponse>> getPostsByUsername(String username) {
    return status(HttpStatus.OK).body(postService.getPostsByUsername(username));
  }

  @PostMapping
  public ResponseEntity<Void> createPost(@RequestBody PostRequest postRequest) {
    postService.save(postRequest);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
