package com.springframework.repository;

import com.springframework.domain.UserPosts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPostRepo extends JpaRepository<UserPosts, Long> {

    UserPosts findByUserId(Long id);
    List<UserPosts> findAllByUserId(Long userId);
}
