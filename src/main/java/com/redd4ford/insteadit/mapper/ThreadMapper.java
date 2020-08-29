package com.redd4ford.insteadit.mapper;

import com.redd4ford.insteadit.dto.ThreadDto;
import com.redd4ford.insteadit.dto.ThreadDto.ThreadDtoBuilder;
import com.redd4ford.insteadit.model.Post;
import com.redd4ford.insteadit.model.Thread;
import com.redd4ford.insteadit.model.Thread.ThreadBuilder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ThreadMapper {

  public ThreadDto mapThreadToDto(Thread thread) {
    if (thread == null) {
      return null;
    }

    ThreadDtoBuilder threadDto = ThreadDto.builder();

    threadDto.id(thread.getId());
    threadDto.name(thread.getName());
    threadDto.description(thread.getDescription());

    threadDto.postCount(mapPosts(thread.getRelatedPosts()));

    return threadDto.build();
  }

  public Thread mapDtoToThread(ThreadDto thread) {
    if (thread == null) {
      return null;
    }

    ThreadBuilder newThread = Thread.builder();

    newThread.id(thread.getId());
    newThread.name(thread.getName());
    newThread.description(thread.getDescription());

    return newThread.build();
  }

  public int mapPosts(List<Post> numberOfPosts) {
    return numberOfPosts.size();
  }

}
