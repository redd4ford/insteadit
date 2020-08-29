package com.redd4ford.insteadit.controller;

import com.redd4ford.insteadit.dto.ThreadDto;
import com.redd4ford.insteadit.service.ThreadService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/threads")
public class ThreadController {

  private final ThreadService threadService;

  public ThreadController(ThreadService threadService) {
    this.threadService = threadService;
  }

  @GetMapping
  public List<ThreadDto> getAllThreads() {
    return threadService.getAll();
  }

  @GetMapping("/{id}")
  public ThreadDto getThread(@PathVariable("id") Long id) {
    return threadService.getThread(id);
  }

  @PostMapping
  public ThreadDto createThread(@RequestBody @Valid ThreadDto threadDto) {
    return threadService.save(threadDto);
  }
}