package com.springframework.services;

import com.springframework.domain.UserPosts;

import java.util.List;

public interface UserPostService {
    UserPosts saveUserPost(Long userId, UserPosts userPosts);
    UserPosts findUserPostByUserId(Long userId, Long postId);
    List<UserPosts> findAllUserPosts();
    UserPosts save(UserPosts userPosts);
    UserPosts findById(Long postId);
}
