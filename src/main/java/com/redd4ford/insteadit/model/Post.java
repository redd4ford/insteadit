package com.redd4ford.insteadit.model;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Entity
public class Post {

  @Id
  @GeneratedValue(strategy = SEQUENCE)
  private Long postId;

  @NotBlank(message = "Post Name cannot be empty or Null")
  private String postName;

  @Nullable
  private String url;

  @Nullable
  @Lob
  private String description;

  private Integer voteCounter;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private User user;
  private Instant createdDate;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "id", referencedColumnName = "id")
  private Thread thread;

  public Post(Long postId, String postName, @Nullable String url, @Nullable String description,
              Integer voteCounter, User user, Instant createdDate, Thread thread) {
    this.postId = postId;
    this.postName = postName;
    this.url = url;
    this.description = description;
    this.voteCounter = voteCounter;
    this.user = user;
    this.createdDate = createdDate;
    this.thread = thread;
  }

  public Post() {
  }

  public Long getPostId() {
    return postId;
  }

  public void setPostId(Long postId) {
    this.postId = postId;
  }

  public String getPostName() {
    return postName;
  }

  public void setPostName(String postName) {
    this.postName = postName;
  }

  @Nullable
  public String getUrl() {
    return url;
  }

  public void setUrl(@Nullable String url) {
    this.url = url;
  }

  @Nullable
  public String getDescription() {
    return description;
  }

  public void setDescription(@Nullable String description) {
    this.description = description;
  }

  public Integer getVoteCounter() {
    return voteCounter;
  }

  public void setVoteCounter(Integer voteCounter) {
    this.voteCounter = voteCounter;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  public Thread getThread() {
    return thread;
  }

  public void setThread(Thread thread) {
    this.thread = thread;
  }

}
