package com.springframework.services;

import com.springframework.domain.Comments;
import com.springframework.domain.UserPosts;
import com.springframework.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;
    private final UserPostsImpl userpostsService;

    public CommentServiceImpl(CommentRepository commentRepository, UserPostsImpl userposts) {
        this.commentRepository = commentRepository;
        this.userpostsService = userposts;
    }


    @Override
    public Comments save(Comments comments) {
        return commentRepository.save(comments);
    }

    @Override
    public List<Comments> getAllCommentsByPostId(Long postId) {

        UserPosts userPosts = userpostsService.findById(postId);
        List<Comments> allComments = userPosts.getComments().stream().collect(Collectors.toList());
        return allComments;
    }
}
