package com.redd4ford.insteadit.dto;

import java.time.Instant;

public class CommentDto {

  private Long id;
  private Long postId;
  private Instant createdDate;
  private String text;
  private String username;

  public CommentDto(Long id, Long postId, Instant createdDate, String text, String username) {
    this.id = id;
    this.postId = postId;
    this.createdDate = createdDate;
    this.text = text;
    this.username = username;
  }

  public CommentDto() {
  }

  public static CommentDtoBuilder builder() {
    return new CommentDtoBuilder();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public static class CommentDtoBuilder {

    private Long id;
    private Long postId;
    private Instant createdDate;
    private String text;
    private String username;

    CommentDtoBuilder() {
    }

    public CommentDtoBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public CommentDtoBuilder postId(Long postId) {
      this.postId = postId;
      return this;
    }

    public CommentDtoBuilder createdDate(Instant createdDate) {
      this.createdDate = createdDate;
      return this;
    }

    public CommentDtoBuilder text(String text) {
      this.text = text;
      return this;
    }

    public CommentDtoBuilder username(String username) {
      this.username = username;
      return this;
    }

    public CommentDto build() {
      return new CommentDto(id, postId, createdDate, text, username);
    }

    public String toString() {
      return "CommentDto.CommentDtoBuilder(id=" + this.id + ", postId=" + this.postId
          + ", createdDate=" + this.createdDate + ", text=" + this.text + ", username="
          + this.username + ")";
    }

  }

}
