package com.springframework.controllers.api;

import com.springframework.domain.Comments;
import com.springframework.domain.PostIdAndComment;
import com.springframework.domain.UserPosts;
import com.springframework.services.CommentService;
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

        commentService.save(comments);

        return comments.getComment();
    }
}
