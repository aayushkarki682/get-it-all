package com.springframework.services;

import com.springframework.domain.User;
import com.springframework.domain.UserPosts;
import com.springframework.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll()
                .stream().collect(Collectors.toList());
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public User save(User user) {

        return userRepository.save(user);
    }

    @Override
    public User checkLoginInfo(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        Optional<User> returnUser = userRepository.findByUserName(userName);
        if(!returnUser.isEmpty()){
            User returnedUser = returnUser.get();
            if(returnedUser.getPassword().equals(password)){
                return returnedUser;
            } else {
                return null;
            }

        }else{
            return null;
        }
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public UserPosts findUserPostById(Long userId, Long postId) {
        User user = findById(userId);
        UserPosts userPost = user.getUserPost(postId);
        return userPost;
    }

    @Override
    public List<UserPosts> findAllUserPosts(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream().collect(Collectors.toList());
    }
}
