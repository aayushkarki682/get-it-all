package com.springframework.services;

import com.springframework.domain.User;

import java.util.List;

public interface UserService{

    User save(User user);
    List<User> getAllUsers();

}
