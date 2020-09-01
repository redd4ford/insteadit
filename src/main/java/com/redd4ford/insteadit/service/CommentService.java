package com.redd4ford.insteadit.service;

import com.redd4ford.insteadit.dto.CommentDto;
import com.redd4ford.insteadit.exception.InsteaditException;
import com.redd4ford.insteadit.mapper.CommentMapper;
import com.redd4ford.insteadit.model.Comment;
import com.redd4ford.insteadit.model.NotificationEmail;
import com.redd4ford.insteadit.model.Post;
import com.redd4ford.insteadit.model.User;
import com.redd4ford.insteadit.repository.CommentRepository;
import com.redd4ford.insteadit.repository.PostRepository;
import com.redd4ford.insteadit.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.redd4ford.insteadit.util.Constants.POST_URL;
import static java.util.stream.Collectors.toList;

@Service
public class CommentService {

  private final PostRepository postRepository;
  private final UserRepository userRepository;
  private final AuthService authService;
  private final CommentMapper commentMapper;
  private final CommentRepository commentRepository;
  private final MailContentBuilder mailContentBuilder;
  private final MailService mailService;

  public CommentService(PostRepository postRepository, UserRepository userRepository,
                        AuthService authService, CommentMapper commentMapper,
                        CommentRepository commentRepository, MailContentBuilder mailContentBuilder,
                        MailService mailService) {
    this.postRepository = postRepository;
    this.userRepository = userRepository;
    this.authService = authService;
    this.commentMapper = commentMapper;
    this.commentRepository = commentRepository;
    this.mailContentBuilder = mailContentBuilder;
    this.mailService = mailService;
  }

  public void save(CommentDto commentDto) {
    Post post = postRepository.findById(commentDto.getPostId())
        .orElseThrow(() -> new InsteaditException("Post not found with id - " +
            commentDto.getPostId().toString()));
    Comment comment = commentMapper.mapDtoToComment(commentDto, post,
        authService.getCurrentUser());
    commentRepository.save(comment);

    if (!comment.getUser().getUsername().equals(post.getUser().getUsername())) {
      String message = mailContentBuilder.build(comment.getUser().getUsername() +
          " has just posted a comment on your post: " + post.getPostName() +
          "<br> Follow the link to read it: " + POST_URL + post.getPostId());
      sendCommentNotification(message, comment.getUser(), post.getUser());
    }
  }

  private void sendCommentNotification(String message, User commenter, User recipient) {
    mailService.sendMail(new NotificationEmail("InsteadIt | " + commenter.getUsername() +
        " has just commented on your post", recipient.getEmail(), message));
  }

  public List<CommentDto> getCommentsByPost(Long postId) {
    Post post = postRepository.findById(postId).orElseThrow(() ->
        new InsteaditException("Post not found with id - " + postId.toString()));
    return commentRepository.findByPost(post)
        .stream()
        .map(commentMapper::mapCommentToDto).collect(toList());
  }

  public List<CommentDto> getCommentsByUser(String userName) {
    User user = userRepository.findByUsername(userName)
        .orElseThrow(() -> new UsernameNotFoundException(userName));
    return commentRepository.findAllByUser(user)
        .stream()
        .map(commentMapper::mapCommentToDto)
        .collect(toList());
  }

}
