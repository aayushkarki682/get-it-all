package com.springframework.services;

import com.springframework.domain.Comments;

import java.util.List;

public interface CommentService {

    Comments save(Comments comments);
    List<Comments> getAllCommentsByPostId(Long postId);
}
