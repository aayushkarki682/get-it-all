package com.springframework.services;

import com.springframework.domain.User;
import com.springframework.domain.UserPosts;

import java.util.List;

public interface UserService{

    User save(User user);
    List<User> getAllUsers();
    long count();
    User checkLoginInfo(User user);
    List<User> findAll();


    User findById(Long id);
    UserPosts findUserPostById(Long userId, Long postId);

    List<UserPosts> findAllUserPosts(User user);
}
