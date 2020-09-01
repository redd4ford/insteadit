package com.redd4ford.insteadit.dto;

public class ThreadDto {

  private Long id;
  private String name;
  private String description;
  private Integer postCounter;

  public ThreadDto(Long id, String name, String description, Integer postCounter) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.postCounter = postCounter;
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

  public Integer getPostCounter() {
    return postCounter;
  }

  public void setPostCounter(Integer postCounter) {
    this.postCounter = postCounter;
  }

  public static class ThreadDtoBuilder {

    private Long id;
    private String name;
    private String description;
    private Integer postCounter;

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

    public ThreadDtoBuilder postCounter(Integer postCounter) {
      this.postCounter = postCounter;
      return this;
    }

    public ThreadDto build() {
      return new ThreadDto(id, name, description, postCounter);
    }

    public String toString() {
      return "ThreadDto.ThreadDtoBuilder(id=" + this.id + ", name=" + this.name + ", description="
          + this.description + ", postCounter=" + this.postCounter + ")";
    }

  }

}
