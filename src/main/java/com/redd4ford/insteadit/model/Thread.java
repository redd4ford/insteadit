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
  private User user;

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

}
