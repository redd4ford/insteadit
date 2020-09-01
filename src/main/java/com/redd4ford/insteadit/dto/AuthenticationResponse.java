package com.redd4ford.insteadit.dto;

import java.time.Instant;

public class AuthenticationResponse {

  private String authenticationToken;
  private String username;
  private String refreshToken;
  private Instant expiresAt;

  public AuthenticationResponse(String authenticationToken, String username, String refreshToken,
                                Instant expiresAt) {
    this.authenticationToken = authenticationToken;
    this.username = username;
    this.refreshToken = refreshToken;
    this.expiresAt = expiresAt;
  }

  public AuthenticationResponse() {
  }

  public static AuthenticationResponseBuilder builder() {
    return new AuthenticationResponseBuilder();
  }

  public String getAuthenticationToken() {
    return authenticationToken;
  }

  public void setAuthenticationToken(String authenticationToken) {
    this.authenticationToken = authenticationToken;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public void setRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public Instant getExpiresAt() {
    return expiresAt;
  }

  public void setExpiresAt(Instant expiresAt) {
    this.expiresAt = expiresAt;
  }

  public static class AuthenticationResponseBuilder {

    private String authenticationToken;
    private String username;
    private String refreshToken;
    private Instant expiresAt;

    AuthenticationResponseBuilder() {
    }

    public AuthenticationResponseBuilder authenticationToken(String authenticationToken) {
      this.authenticationToken = authenticationToken;
      return this;
    }

    public AuthenticationResponseBuilder username(String username) {
      this.username = username;
      return this;
    }

    public AuthenticationResponseBuilder refreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    public AuthenticationResponseBuilder expiresAt(Instant expiresAt) {
      this.expiresAt = expiresAt;
      return this;
    }

    public AuthenticationResponse build() {
      return new AuthenticationResponse(authenticationToken, username, refreshToken, expiresAt);
    }

    public String toString() {
      return "AuthenticationResponse.AuthenticationResponseBuilder(authenticationToken="
          + this.authenticationToken + ", username=" + this.username + ", refreshToken="
          + this.refreshToken + ", expiresAt=" + this.expiresAt + ")";
    }
  }
}
