package com.redd4ford.insteadit.model;

import javax.validation.constraints.NotNull;

import javax.persistence.*;

@Entity
public class Vote {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long voteId;

  private VoteType voteType;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "postId", referencedColumnName = "postId")
  private Post post;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private User user;

  public Vote(Long voteId, VoteType voteType, Post post, User user) {
    this.voteId = voteId;
    this.voteType = voteType;
    this.post = post;
    this.user = user;
  }

  public Vote() {
  }

  public Long getVoteId() {
    return voteId;
  }

  public void setVoteId(Long voteId) {
    this.voteId = voteId;
  }

  public VoteType getVoteType() {
    return voteType;
  }

  public void setVoteType(VoteType voteType) {
    this.voteType = voteType;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

}
