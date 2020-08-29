package com.redd4ford.insteadit.repository;

import com.redd4ford.insteadit.model.Thread;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThreadRepository extends JpaRepository<Thread, Long> {

  Thread findByName(String threadName);

}
