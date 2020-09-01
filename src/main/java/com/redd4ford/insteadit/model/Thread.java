package com.redd4ford.insteadit.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

@Entity
public class Thread {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @NotBlank(message = "A thread should have its name")
  private String name;

  @NotBlank(message = "Description cannot be empty")
  private String description;

  @OneToMany(fetch = FetchType.LAZY)
  private List<Post> relatedPosts;

  private Instant createdDate;

  @ManyToOne(fetch = FetchType.LAZY)
  private com.redd4ford.insteadit.model.User user;

  public Thread(Long id, String name, String description, List<Post> relatedPosts,
                Instant createdDate, User user) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.relatedPosts = relatedPosts;
    this.createdDate = createdDate;
    this.user = user;
  }

  public Thread() {
  }

  public static ThreadBuilder builder() {
    return new ThreadBuilder();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Post> getRelatedPosts() {
    return relatedPosts;
  }

  public void setRelatedPosts(List<Post> relatedPosts) {
    this.relatedPosts = relatedPosts;
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

  public static class ThreadBuilder {

    private Long id;
    private @NotBlank(message = "A thread should have its name") String name;
    private @NotBlank(message = "Description cannot be empty") String description;
    private List<Post> relatedPosts;
    private Instant createdDate;
    private User user;

    ThreadBuilder() {
    }

    public ThreadBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public ThreadBuilder name(@NotBlank(message = "A thread should have its name") String name) {
      this.name = name;
      return this;
    }

    public ThreadBuilder description(@NotBlank(message = "Description cannot be empty")
                                         String description) {
      this.description = description;
      return this;
    }

    public ThreadBuilder relatedPosts(List<Post> relatedPosts) {
      this.relatedPosts = relatedPosts;
      return this;
    }

    public ThreadBuilder createdDate(Instant createdDate) {
      this.createdDate = createdDate;
      return this;
    }

    public ThreadBuilder user(User user) {
      this.user = user;
      return this;
    }

    public Thread build() {
      return new Thread(id, name, description, relatedPosts, createdDate, user);
    }

    public String toString() {
      return "Thread.ThreadBuilder(id=" + this.id + ", name=" + this.name + ", description="
          + this.description + ", relatedPosts=" + this.relatedPosts + ", createdDate="
          + this.createdDate + ", user=" + this.user + ")";
    }

  }

}
