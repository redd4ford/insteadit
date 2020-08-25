package com.redd4ford.insteadit.dto;

public class ThreadDto {
  private Long id;
  private String name;
  private String description;
  private Integer postCount;

  public ThreadDto(Long id, String name, String description, Integer postCount) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.postCount = postCount;
  }

  public ThreadDto() {
  }

  public static ThreadDtoBuilder builder() {
    return new ThreadDtoBuilder();
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

  public Integer getPostCount() {
    return postCount;
  }

  public void setPostCount(Integer postCount) {
    this.postCount = postCount;
  }

  public static class ThreadDtoBuilder {
    private Long id;
    private String name;
    private String description;
    private Integer postCount;

    ThreadDtoBuilder() {
    }

    public ThreadDtoBuilder id(Long id) {
      this.id = id;
      return this;
    }

    public ThreadDtoBuilder name(String name) {
      this.name = name;
      return this;
    }

    public ThreadDtoBuilder description(String description) {
      this.description = description;
      return this;
    }

    public ThreadDtoBuilder postCount(Integer postCount) {
      this.postCount = postCount;
      return this;
    }

    public ThreadDto build() {
      return new ThreadDto(id, name, description, postCount);
    }

    public String toString() {
      return "ThreadDto.ThreadDtoBuilder(id=" + this.id + ", name=" + this.name + ", description="
          + this.description + ", postCount=" + this.postCount + ")";
    }
  }
}
