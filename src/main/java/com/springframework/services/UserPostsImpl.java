package com.springframework.services;

import com.springframework.domain.User;
import com.springframework.domain.UserPosts;
import com.springframework.repository.UserPostRepo;
import com.springframework.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserPostsImpl implements UserPostService{

    private final UserPostRepo userPostRepo;
    private final UserRepository userRepository;

    public UserPostsImpl(UserPostRepo userPostRepo, UserRepository userRepository) {
        this.userPostRepo = userPostRepo;
        this.userRepository = userRepository;
    }



    @Override
    @Transactional
    public UserPosts saveUserPost(Long userId, UserPosts userPosts) {
        User user = userRepository.findById(userId).get();
        user.addUserPost(userPosts);
        System.out.println(user.getUserPosts().stream().count());
        userPosts.setUser(user);
        userRepository.save(user);
        return userPostRepo.findByUserId(userId);

    }

    @Override
    public UserPosts findUserPostByUserId(Long userId, Long postId) {

        return null;
    }
}
