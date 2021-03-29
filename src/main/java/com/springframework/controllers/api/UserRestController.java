package com.springframework.controllers.api;

import com.springframework.domain.Comments;
import com.springframework.domain.PostIdAndComment;
import com.springframework.domain.User;
import com.springframework.domain.UserPosts;
import com.springframework.services.CommentService;
import com.springframework.services.UserPostService;
import com.springframework.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api/")
public class UserRestController {

    private final UserPostService userPostService;
    private final UserService userService;
    private final CommentService commentService;


    public UserRestController(UserPostService userPostService, UserService userService, CommentService commentService) {
        this.userPostService = userPostService;
        this.userService = userService;
        this.commentService = commentService;
    }

    @PostMapping("/likeClick")
    public int addLikeValue(@RequestBody UserPosts userPosts){
        System.out.println(userPosts.getId());
        UserPosts postToSaveLike = userPostService.findById(userPosts.getId());

        postToSaveLike.addUserLike();
        userPostService.save(postToSaveLike);
       return userPostService.findById(postToSaveLike.getId()).getLikePressed();

    }

    @PostMapping("/saveComment")
    public String saveComment(@RequestBody PostIdAndComment postIdAndComment){
        UserPosts userPosts = userPostService.findById(postIdAndComment.getId());
        System.out.println(userPosts.getId());
        Comments comments = Comments.builder().comment(postIdAndComment.getComment()).build();
        comments.setUserPosts(userPosts);
        userPosts.getComments().add(comments);

        Comments savedComment = commentService.save(comments);

        return String.valueOf(savedComment.getId());
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/forUser/{id}")
    public User getForUser(@PathVariable Long id){
        return userService.findById(id);
    }

    @GetMapping("/forUserPost/{postId}")
    public UserPosts forUserPost(@PathVariable Long postId){
        return userPostService.findById(postId);
    }
}
