package com.redd4ford.insteadit.repository;

import com.redd4ford.insteadit.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

  Optional<Thread> findByName(String threadName);

}
