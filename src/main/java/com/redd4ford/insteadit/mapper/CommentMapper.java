package com.redd4ford.insteadit.mapper;

import com.redd4ford.insteadit.dto.CommentDto;
import com.redd4ford.insteadit.model.Comment;
import com.redd4ford.insteadit.model.Post;
import com.redd4ford.insteadit.model.User;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public Comment mapDtoToComment(CommentDto commentDto, Post post, User user) {
    if (commentDto == null && post == null && user == null) {
      return null;
    }

    Comment.CommentBuilder comment = Comment.builder();

    if (commentDto != null) {
      comment.text(commentDto.getText());
    }
    if (post != null) {
      comment.post(post);
    }
    if (user != null) {
      comment.user(user);
    }
    comment.createdDate(java.time.Instant.now());

    return comment.build();
  }

  public CommentDto mapCommentToDto(Comment comment) {
    if (comment == null) {
      return null;
    }

    CommentDto.CommentDtoBuilder commentDto = CommentDto.builder();

    commentDto.id(comment.getId());
    commentDto.createdDate(comment.getCreatedDate());
    commentDto.text(comment.getText());
    commentDto.postId(comment.getPost().getPostId());
    commentDto.username(comment.getUser().getUsername());

    return commentDto.build();
  }

}
