package com.redd4ford.insteadit.repository;

import com.redd4ford.insteadit.model.Post;
import com.redd4ford.insteadit.model.User;
import com.redd4ford.insteadit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

  Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc (Post post, User currentUser);

}
