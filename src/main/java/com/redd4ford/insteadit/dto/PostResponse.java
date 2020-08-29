package com.redd4ford.insteadit.dto;

public class PostResponse {
  private Long id;
  private String postName;
  private String url;
  private String description;
  private String username;
  private String threadName;
  private Integer voteCount;
  private Integer commentCount;
  private String duration;
  private boolean upVote;
  private boolean downVote;

  public PostResponse(Long id, String postName, String url, String description, String username,
                      String threadName, Integer voteCount, Integer commentCount, String duration,
                      boolean upVote, boolean downVote) {
    this.id = id;
    this.postName = postName;
    this.url = url;
    this.description = description;
    this.username = username;
    this.threadName = threadName;
    this.voteCount = voteCount;
    this.commentCount = commentCount;
    this.duration = duration;
    this.upVote = upVote;
    this.downVote = downVote;
  }

  public PostResponse() {
  }

  public static PostResponseBuilder builder() {
    return new PostResponseBuilder();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPostName() {
    return postName;
  }

  public void setPostName(String postName) {
    this.postName = postName;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getThreadName() {
    return threadName;
  }

  public void setThreadName(String threadName) {
    this.threadName = threadName;
  }

  public Integer getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(Integer voteCount) {
    this.voteCount = voteCount;
  }

  public Integer getCommentCount() {
    return commentCount;
  }

  public void setCommentCount(Integer commentCount) {
    this.commentCount = commentCount;
  }

  public String getDuration() {
    return duration;
  }

  public void setDuration(String duration) {
    this.duration = duration;
  }

  public boolean isUpVote() {
    return upVote;
  }

  public void setUpVote(boolean upVote) {
    this.upVote = upVote;
  }

  public boolean isDownVote() {
    return downVote;
  }

  public void setDownVote(boolean downVote) {
    this.downVote = downVote;
  }

  public static class PostResponseBuilder {
    private Long id;
    private String postName;
    private String url;
    private String description;
    private String username;
    private String threadName;
    private Integer voteCount;
    private Integer commentCount;
    private String duration;
    private boolean upVote;
    private boolean downVote;

    PostResponseBuilder() {
    }

    public PostResponseBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public PostResponseBuilder postName(String postName) {
      this.postName = postName;
      return this;
    }

    public PostResponseBuilder url(String url) {
      this.url = url;
      return this;
    }

    public PostResponseBuilder description(String description) {
      this.description = description;
      return this;
    }

    public PostResponseBuilder username(String username) {
      this.username = username;
      return this;
    }

    public PostResponseBuilder threadName(String threadName) {
      this.threadName = threadName;
      return this;
    }

    public PostResponseBuilder voteCount(Integer voteCount) {
      this.voteCount = voteCount;
      return this;
    }

    public PostResponseBuilder commentCount(Integer commentCount) {
      this.commentCount = commentCount;
      return this;
    }

    public PostResponseBuilder duration(String duration) {
      this.duration = duration;
      return this;
    }

    public PostResponseBuilder upVote(boolean upVote) {
      this.upVote = upVote;
      return this;
    }

    public PostResponseBuilder downVote(boolean downVote) {
      this.downVote = downVote;
      return this;
    }

    public PostResponse build() {
      return new PostResponse(id, postName, url, description, username, threadName, voteCount,
          commentCount, duration, upVote, downVote);
    }

    public String toString() {
      return "PostResponse.PostResponseBuilder(id=" + this.id + ", postName=" + this.postName
          + ", url=" + this.url + ", description=" + this.description + ", username="
          + this.username + ", threadName=" + this.threadName + ", voteCount=" + this.voteCount
          + ", commentCount=" + this.commentCount + ", duration=" + this.duration + ", upVote="
          + this.upVote + ", downVote=" + this.downVote + ")";
    }
  }
}
