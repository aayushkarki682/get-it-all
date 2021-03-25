package com.springframework.controllers.api;

import com.springframework.domain.UserPosts;
import com.springframework.services.UserPostService;
import com.springframework.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/api/")
public class UserRestController {

    private final UserPostService userPostService;
    private final UserService userService;

    public UserRestController(UserPostService userPostService, UserService userService) {
        this.userPostService = userPostService;
        this.userService = userService;
    }

    @PostMapping("/likeClick")
    public int addLikeValue(@RequestBody UserPosts userPosts){
        System.out.println(userPosts.getId());
        UserPosts postToSaveLike = userPostService.findById(userPosts.getId());

        postToSaveLike.addUserLike();
        userPostService.save(postToSaveLike);
       return userPostService.findById(postToSaveLike.getId()).getLikePressed();

    }
}
