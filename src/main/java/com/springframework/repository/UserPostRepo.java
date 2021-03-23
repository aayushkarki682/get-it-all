package com.springframework.repository;

import com.springframework.domain.UserPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPostRepo extends JpaRepository<UserPosts, Long> {

    UserPosts findByUserId(Long id);
}
