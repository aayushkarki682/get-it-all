package com.springframework.services;

import com.springframework.domain.User;
import com.springframework.domain.UserPosts;
import com.springframework.repository.UserPostRepo;
import com.springframework.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        System.out.println("signed up user id after creating post + "+user.getId());
        userPosts.setUser(user);
        user.getUserPosts().add(userPosts);
        System.out.println("after adding new post user post size + " + user.getUserPosts().size());


        userRepository.save(user);

        return userPostRepo.findByUserId(userPosts.getId());

    }

    @Override
    public UserPosts findUserPostByUserId(Long userId, Long postId) {

        return null;
    }

    @Override
    public List<UserPosts> findAllUserPosts() {
        return userPostRepo.findAll();
    }

    @Override
    public UserPosts save(UserPosts userPosts) {
        return userPostRepo.save(userPosts);
    }

    @Override
    public UserPosts findById(Long postId) {
        return userPostRepo.findById(postId).get();
    }
}
