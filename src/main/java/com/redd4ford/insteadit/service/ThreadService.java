package com.redd4ford.insteadit.service;

import com.redd4ford.insteadit.dto.ThreadDto;
import com.redd4ford.insteadit.exception.InsteaditException;
import com.redd4ford.insteadit.model.Thread;
import com.redd4ford.insteadit.repository.ThreadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.time.Instant.now;
import static java.util.stream.Collectors.toList;

@Service
public class ThreadService {

  private final ThreadRepository threadRepository;
  private final AuthService authService;

  public ThreadService(ThreadRepository threadRepository, AuthService authService) {
    this.threadRepository = threadRepository;
    this.authService = authService;
  }

  @Transactional(readOnly = true)
  public List<ThreadDto> getAll() {
    return threadRepository.findAll()
        .stream()
        .map(this::mapToDto)
        .collect(toList());
  }

  @Transactional
  public ThreadDto save(ThreadDto threadDto) {
    Thread thread = threadRepository.save(mapToThread(threadDto));
    threadDto.setId(thread.getId());
    return threadDto;
  }

  @Transactional(readOnly = true)
  public ThreadDto getThread(Long id) {
    Thread thread = threadRepository.findById(id)
        .orElseThrow(() -> new InsteaditException("Thread not found with id -" + id));
    return mapToDto(thread);
  }

  private ThreadDto mapToDto(Thread thread) {
    return ThreadDto.builder().name(thread.getName())
        .id(thread.getId())
        .postCount(thread.getRelatedPosts().size())
        .build();
  }

  private Thread mapToThread(ThreadDto threadDto) {
    return Thread.builder().name("/r/" + threadDto.getName())
        .description(threadDto.getDescription())
        .user(authService.getCurrentUser())
        .createdDate(now()).build();
  }
}
