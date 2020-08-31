package com.redd4ford.insteadit.model;

import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name = "\"user\"")
@Proxy(lazy = false)
public class User {

  @Id
  @GeneratedValue(strategy = SEQUENCE)
  private Long userId;

  @NotBlank(message = "Username cannot be empty")
  private String username;

  @NotBlank(message = "Password cannot be empty")
  private String password;

  @Email
  @NotEmpty(message = "Email cannot be empty")
  private String email;

  private Instant created;

  private boolean enabled;

  public User(Long userId, String username, String password, String email, Instant created,
              boolean enabled) {
    this.userId = userId;
    this.username = username;
    this.password = password;
    this.email = email;
    this.created = created;
    this.enabled = enabled;
  }

  public User() {
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Instant getCreated() {
    return created;
  }

  public void setCreated(Instant created) {
    this.created = created;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

}
