package com.springframework.services;

import com.springframework.domain.User;
import com.springframework.domain.UserPosts;

import java.util.List;

public interface UserService{

    User save(User user);
    List<User> getAllUsers();
    long count();
    String checkLoginInfo(User user);


    User findById(Long id);
    UserPosts findUserPostById(Long userId, Long postId);
}
