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

  private Integer voteCounter = 0;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "userId", referencedColumnName = "userId")
  private com.redd4ford.insteadit.model.User user;

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

  public static PostBuilder builder() {
    return new PostBuilder();
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

  public static class PostBuilder {
    private Long postId;
    private @NotBlank(message = "Post Name cannot be empty or Null") String postName;
    private String url;
    private String description;
    private Integer voteCounter = 0;
    private User user;
    private Instant createdDate;
    private Thread thread;

    PostBuilder() {
    }

    public PostBuilder postId(Long postId) {
      this.postId = postId;
      return this;
    }

    public PostBuilder postName(@NotBlank(message = "Post Name cannot be empty or Null")
                                    String postName) {
      this.postName = postName;
      return this;
    }

    public PostBuilder url(String url) {
      this.url = url;
      return this;
    }

    public PostBuilder description(String description) {
      this.description = description;
      return this;
    }

    public PostBuilder voteCounter(Integer voteCounter) {
      this.voteCounter = voteCounter;
      return this;
    }

    public PostBuilder user(User user) {
      this.user = user;
      return this;
    }

    public PostBuilder createdDate(Instant createdDate) {
      this.createdDate = createdDate;
      return this;
    }

    public PostBuilder thread(Thread thread) {
      this.thread = thread;
      return this;
    }

    public Post build() {
      return new Post(postId, postName, url, description, voteCounter, user, createdDate, thread);
    }

    public String toString() {
      return "Post.PostBuilder(postId=" + this.postId + ", postName=" + this.postName + ", url="
          + this.url + ", description=" + this.description + ", voteCounter="
          + this.voteCounter + ", user=" + this.user + ", createdDate="
          + this.createdDate + ", thread=" + this.thread + ")";
    }
  }
}
