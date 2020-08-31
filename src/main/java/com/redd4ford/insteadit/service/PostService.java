package com.redd4ford.insteadit.service;

import com.redd4ford.insteadit.dto.PostRequest;
import com.redd4ford.insteadit.dto.PostResponse;
import com.redd4ford.insteadit.exception.InsteaditException;
import com.redd4ford.insteadit.mapper.PostMapper;
import com.redd4ford.insteadit.model.Post;
import com.redd4ford.insteadit.model.Thread;
import com.redd4ford.insteadit.model.User;
import com.redd4ford.insteadit.repository.PostRepository;
import com.redd4ford.insteadit.repository.ThreadRepository;
import com.redd4ford.insteadit.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class PostService {

  private final PostRepository postRepository;
  private final ThreadRepository threadRepository;
  private final UserRepository userRepository;
  private final AuthService authService;
  private final PostMapper postMapper;

  public PostService(PostRepository postRepository, ThreadRepository threadRepository,
                     UserRepository userRepository, AuthService authService,
                     PostMapper postMapper) {
    this.postRepository = postRepository;
    this.threadRepository = threadRepository;
    this.userRepository = userRepository;
    this.authService = authService;
    this.postMapper = postMapper;
  }

  @Transactional(readOnly = true)
  public PostResponse getPost(Long id) {
    Post post = postRepository.findById(id)
        .orElseThrow(() -> new InsteaditException("Post not found with id - " + id.toString()));
    return postMapper.mapPostToDto(post);
  }

  @Transactional(readOnly = true)
  public List<PostResponse> getAllPosts() {
    return postRepository.findAll()
        .stream()
        .map(postMapper::mapPostToDto)
        .collect(toList());
  }

  @Transactional(readOnly = true)
  public List<PostResponse> getPostsByThread(Long threadId) {
    Thread thread = threadRepository.findById(threadId)
        .orElseThrow(() -> new InsteaditException(threadId.toString()));
    List<Post> posts = postRepository.findAllByThread(thread);
    return posts.stream().map(postMapper::mapPostToDto).collect(toList());
  }

  @Transactional(readOnly = true)
  public List<PostResponse> getPostsByUsername(String username) {
    User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException(username));
    return postRepository.findByUser(user)
        .stream()
        .map(postMapper::mapPostToDto)
        .collect(toList());
  }

  public void save(PostRequest postRequest) {
    Thread thread = threadRepository.findByName(postRequest.getThreadName())
        .orElseThrow(() -> new InsteaditException("Thread not found with name - " +
            postRequest.getThreadName()));
    postRepository.save(postMapper.mapDtoToPost(postRequest, thread,
        authService.getCurrentUser()));
  }

}
