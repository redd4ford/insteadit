package com.redd4ford.insteadit.service;

import com.redd4ford.insteadit.dto.VoteDto;
import com.redd4ford.insteadit.exception.InsteaditException;
import com.redd4ford.insteadit.model.Post;
import com.redd4ford.insteadit.model.Vote;
import com.redd4ford.insteadit.model.VoteType;
import com.redd4ford.insteadit.repository.PostRepository;
import com.redd4ford.insteadit.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteService {

  private final VoteRepository voteRepository;
  private final PostRepository postRepository;
  private final AuthService authService;

  public VoteService(VoteRepository voteRepository, PostRepository postRepository, AuthService authService) {
    this.voteRepository = voteRepository;
    this.postRepository = postRepository;
    this.authService = authService;
  }

  @Transactional
  public void vote(VoteDto voteDto) {
    Post post = postRepository.findById(voteDto.getPostId())
        .orElseThrow(() -> new InsteaditException("Post Not Found with ID - " +
            voteDto.getPostId()));
    Optional<Vote> voteByPostAndUser =
        voteRepository.findTopByPostAndUserOrderByVoteIdDesc(post, authService.getCurrentUser());
    if (voteByPostAndUser.isPresent() &&
        voteByPostAndUser.get().getVoteType()
            .equals(voteDto.getVoteType())) {
      throw new InsteaditException("You have already "
          + voteDto.getVoteType() + "'d for this post");
    }
    if (VoteType.UPVOTE.equals(voteDto.getVoteType())) {
      post.setVoteCounter(post.getVoteCounter() + 1);
    } else {
      post.setVoteCounter(post.getVoteCounter() - 1);
    }
    voteRepository.save(mapToVote(voteDto, post));
    postRepository.save(post);
  }

  private Vote mapToVote(VoteDto voteDto, Post post) {
    return Vote.builder()
        .voteType(voteDto.getVoteType())
        .post(post)
        .user(authService.getCurrentUser())
        .build();
  }

}
