package com.springframework.services;

import com.springframework.domain.User;
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
    public String checkLoginInfo(User user) {
        String userName = user.getUserName();
        String password = user.getPassword();
        Optional<User> returnUser = Optional.ofNullable(userRepository.findByUserName(userName));
        if(!returnUser.isEmpty()){
            return "It is valid";
        }else{
            return null;
        }
    }
}
