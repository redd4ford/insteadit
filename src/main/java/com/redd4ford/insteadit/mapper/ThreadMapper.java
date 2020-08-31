package com.redd4ford.insteadit.mapper;

import com.redd4ford.insteadit.dto.ThreadDto;
import com.redd4ford.insteadit.dto.ThreadDto.ThreadDtoBuilder;
import com.redd4ford.insteadit.model.Thread;
import com.redd4ford.insteadit.model.Thread.ThreadBuilder;
import com.redd4ford.insteadit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ThreadMapper {

  @Autowired
  private PostRepository postRepository;

  public ThreadDto mapThreadToDto(Thread thread) {
    if (thread == null) {
      return null;
    }

    ThreadDtoBuilder threadDto = ThreadDto.builder();

    threadDto.id(thread.getId());
    threadDto.name(thread.getName());
    threadDto.description(thread.getDescription());

    threadDto.postCounter(postCounter(thread));

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

  Integer postCounter(Thread thread) {
    return postRepository.findAllByThread(thread).size();
  }

}
