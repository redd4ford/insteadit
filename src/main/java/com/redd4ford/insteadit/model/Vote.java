package com.redd4ford.insteadit.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
  private com.redd4ford.insteadit.model.User user;

  public Vote(Long voteId, VoteType voteType, Post post, User user) {
    this.voteId = voteId;
    this.voteType = voteType;
    this.post = post;
    this.user = user;
  }

  public Vote() {
  }

  public static VoteBuilder builder() {
    return new VoteBuilder();
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

  public static class VoteBuilder {

    private Long voteId;
    private VoteType voteType;
    private @NotNull Post post;
    private User user;

    VoteBuilder() {
    }

    public VoteBuilder voteId(Long voteId) {
      this.voteId = voteId;
      return this;
    }

    public VoteBuilder voteType(VoteType voteType) {
      this.voteType = voteType;
      return this;
    }

    public VoteBuilder post(@NotNull Post post) {
      this.post = post;
      return this;
    }

    public VoteBuilder user(User user) {
      this.user = user;
      return this;
    }

    public Vote build() {
      return new Vote(voteId, voteType, post, user);
    }

    public String toString() {
      return "Vote.VoteBuilder(voteId=" + this.voteId + ", voteType=" + this.voteType + ", post="
          + this.post + ", user=" + this.user + ")";
    }

  }

}
