package com.redd4ford.insteadit.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

@Entity
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotEmpty
  private String text;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "postId", referencedColumnName = "postId")
  private Post post;

  private Instant createdDate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private com.redd4ford.insteadit.model.User user;

  public Comment(Long id, String text, Post post, Instant createdDate, User user) {
    this.id = id;
    this.text = text;
    this.post = post;
    this.createdDate = createdDate;
    this.user = user;
  }

  public Comment() {
  }

  public static CommentBuilder builder() {
    return new CommentBuilder();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public static class CommentBuilder {

    private Long id;
    private @NotEmpty String text;
    private Post post;
    private Instant createdDate;
    private User user;

    CommentBuilder() {
    }

    public CommentBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public CommentBuilder text(@NotEmpty String text) {
      this.text = text;
      return this;
    }

    public CommentBuilder post(Post post) {
      this.post = post;
      return this;
    }

    public CommentBuilder createdDate(Instant createdDate) {
      this.createdDate = createdDate;
      return this;
    }

    public CommentBuilder user(User user) {
      this.user = user;
      return this;
    }

    public Comment build() {
      return new Comment(id, text, post, createdDate, user);
    }

    public String toString() {
      return "Comment.CommentBuilder(id=" + this.id + ", text=" + this.text + ", post=" + this.post
          + ", createdDate=" + this.createdDate + ", user=" + this.user + ")";
    }

  }

}
