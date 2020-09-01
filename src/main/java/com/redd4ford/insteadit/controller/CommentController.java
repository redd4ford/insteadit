package com.redd4ford.insteadit.controller;

import com.redd4ford.insteadit.dto.CommentDto;
import com.redd4ford.insteadit.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

  private final CommentService commentService;

  public CommentController(CommentService commentService) {
    this.commentService = commentService;
  }

  @GetMapping("/by-post/{id}")
  public ResponseEntity<List<CommentDto>> getAllCommentsByPost(@PathVariable("id")
                                                                   Long postId) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(commentService.getCommentsByPost(postId));
  }

  @GetMapping("/by-user/{username}")
  public ResponseEntity<List<CommentDto>> getAllCommentsByUser(@PathVariable("username")
                                                                   String username) {
    return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentsByUser(username));
  }

  @PostMapping
  public ResponseEntity<Void> createComment(@RequestBody CommentDto commentDto) {
    commentService.save(commentDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
