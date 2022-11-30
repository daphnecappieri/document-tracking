package com.daphne.visiblethread.documenttracking.repo;

import com.daphne.visiblethread.documenttracking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
