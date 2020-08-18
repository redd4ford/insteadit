package com.redd4ford.insteadit.repository;

import com.redd4ford.insteadit.model.Post;
import com.redd4ford.insteadit.model.Thread;
import com.redd4ford.insteadit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

  List<Post> findAllByThread(Thread thread);

  List<Post> findByUser(User user);

}
