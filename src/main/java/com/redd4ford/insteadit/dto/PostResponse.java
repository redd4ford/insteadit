package com.redd4ford.insteadit.dto;

public class PostResponse {
  private Long id;
  private String postName;
  private String url;
  private String description;
  private String username;
  private String threadName;
  private Integer voteCounter;
  private Integer commentCounter;
  private String duration;
  private boolean upVote;
  private boolean downVote;

  public PostResponse(Long id, String postName, String url, String description, String username,
                      String threadName, Integer voteCounter, Integer commentCounter, String duration,
                      boolean upVote, boolean downVote) {
    this.id = id;
    this.postName = postName;
    this.url = url;
    this.description = description;
    this.username = username;
    this.threadName = threadName;
    this.voteCounter = voteCounter;
    this.commentCounter = commentCounter;
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

  public Integer getVoteCounter() {
    return voteCounter;
  }

  public void setVoteCounter(Integer voteCounter) {
    this.voteCounter = voteCounter;
  }

  public Integer getCommentCounter() {
    return commentCounter;
  }

  public void setCommentCounter(Integer commentCounter) {
    this.commentCounter = commentCounter;
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
    private Integer voteCounter;
    private Integer commentCounter;
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

    public PostResponseBuilder voteCounter(Integer voteCounter) {
      this.voteCounter = voteCounter;
      return this;
    }

    public PostResponseBuilder commentCounter(Integer commentCounter) {
      this.commentCounter = commentCounter;
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
      return new PostResponse(id, postName, url, description, username, threadName, voteCounter,
          commentCounter, duration, upVote, downVote);
    }

    public String toString() {
      return "PostResponse.PostResponseBuilder(id=" + this.id + ", postName=" + this.postName
          + ", url=" + this.url + ", description=" + this.description + ", username="
          + this.username + ", threadName=" + this.threadName + ", voteCounter=" + this.voteCounter
          + ", commentCounter=" + this.commentCounter + ", duration=" + this.duration + ", upVote="
          + this.upVote + ", downVote=" + this.downVote + ")";
    }
  }
}
