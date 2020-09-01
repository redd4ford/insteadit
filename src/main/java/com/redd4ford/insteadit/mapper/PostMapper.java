package com.redd4ford.insteadit.mapper;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.redd4ford.insteadit.dto.PostRequest;
import com.redd4ford.insteadit.dto.PostResponse;
import com.redd4ford.insteadit.model.Thread;
import com.redd4ford.insteadit.model.*;
import com.redd4ford.insteadit.repository.CommentRepository;
import com.redd4ford.insteadit.repository.VoteRepository;
import com.redd4ford.insteadit.service.AuthService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.redd4ford.insteadit.model.VoteType.DOWNVOTE;
import static com.redd4ford.insteadit.model.VoteType.UPVOTE;

@Component
public class PostMapper {

  @Autowired
  private CommentRepository commentRepository;
  @Autowired
  private VoteRepository voteRepository;
  @Autowired
  private AuthService authService;

  public Post mapDtoToPost(PostRequest postRequest, Thread thread, User user) {
    if (postRequest == null && thread == null && user == null) {
      return null;
    }

    Post.PostBuilder post = Post.builder();

    if (postRequest != null) {
      post.description(postRequest.getDescription());
      post.postId(postRequest.getPostId());
      post.postName(postRequest.getPostName());
      post.url(postRequest.getUrl());
    }
    if (thread != null) {
      post.thread(thread);
    }
    if (user != null) {
      post.user(user);
    }
    post.voteCounter(0);
    post.createdDate(java.time.Instant.now());

    return post.build();
  }

  public PostResponse mapPostToDto(Post post) {
    if (post == null) {
      return null;
    }

    PostResponse.PostResponseBuilder postResponse = PostResponse.builder();

    postResponse.id(post.getPostId());
    postResponse.threadName(post.getThread().getName());
    postResponse.username(post.getUser().getUsername());
    postResponse.postName(post.getPostName());
    postResponse.url(post.getUrl());
    postResponse.description(post.getDescription());

    postResponse.duration(getDuration(post));
    postResponse.commentCounter(commentCounter(post));
    postResponse.voteCounter(post.getVoteCounter());
    postResponse.upVote(isPostUpVoted(post));
    postResponse.downVote(isPostDownVoted(post));

    return postResponse.build();
  }

  private String postThreadName(Post post) {
    if (post == null) {
      return null;
    }
    Thread thread = post.getThread();
    if (thread == null) {
      return null;
    }
    return thread.getName();
  }

  private String postUserUsername(Post post) {
    if (post == null) {
      return null;
    }
    User user = post.getUser();
    if (user == null) {
      return null;
    }
    String username = user.getUsername();
    if (username == null) {
      return null;
    }
    return username;
  }

  Integer commentCounter(Post post) {
    return commentRepository.findByPost(post).size();
  }

  String getDuration(@NotNull Post post) {
    return TimeAgo.using(post.getCreatedDate().toEpochMilli());
  }

  boolean isPostUpVoted(Post post) {
    return checkVoteType(post, UPVOTE);
  }

  boolean isPostDownVoted(Post post) {
    return checkVoteType(post, DOWNVOTE);
  }

  private boolean checkVoteType(Post post, VoteType voteType) {
    if (authService.isLoggedIn()) {
      Optional<Vote> voteForPostByUser =
          voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post,
              authService.getCurrentUser());
      return voteForPostByUser.filter(vote -> vote.getVoteType().equals(voteType))
          .isPresent();
    }

    return false;
  }

}
