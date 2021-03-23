package com.springframework.services;

import com.springframework.domain.UserPosts;

public interface UserPostService {
    UserPosts saveUserPost(Long userId, UserPosts userPosts);
    UserPosts findUserPostByUserId(Long userId, Long postId);
}
