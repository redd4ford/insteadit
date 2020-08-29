package com.redd4ford.insteadit.service;

import com.redd4ford.insteadit.dto.ThreadDto;
import com.redd4ford.insteadit.exception.InsteaditException;
import com.redd4ford.insteadit.mapper.ThreadMapper;
import com.redd4ford.insteadit.model.Thread;
import com.redd4ford.insteadit.repository.ThreadRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ThreadService {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ThreadService.class);
  private final ThreadRepository threadRepository;
  private final ThreadMapper threadMapper;

  public ThreadService(ThreadRepository threadRepository, ThreadMapper threadMapper) {
    this.threadRepository = threadRepository;
    this.threadMapper = threadMapper;
  }

  @Transactional(readOnly = true)
  public List<ThreadDto> getAll() {
    return threadRepository.findAll()
        .stream()
        .map(threadMapper::mapThreadToDto)
        .collect(toList());
  }

  @Transactional(readOnly = true)
  public ThreadDto getThread(Long id) {
    Thread thread = threadRepository.findById(id)
        .orElseThrow(() -> new InsteaditException("Thread not found with id -" + id));
    return threadMapper.mapThreadToDto(thread);
  }

  @Transactional
  public ThreadDto save(ThreadDto threadDto) {
    Thread thread = threadRepository.save(threadMapper.mapDtoToThread(threadDto));
    threadDto.setId(thread.getId());
    return threadDto;
  }

}
