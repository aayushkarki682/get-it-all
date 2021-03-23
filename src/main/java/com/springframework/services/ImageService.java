package com.springframework.services;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    void saveImageFile(Long userId,Long postId, MultipartFile file);
}
