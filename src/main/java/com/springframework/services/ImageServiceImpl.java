package com.springframework.services;

import com.springframework.domain.User;
import com.springframework.domain.UserPosts;
import com.springframework.repository.UserPostRepo;
import com.springframework.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {

    private final UserPostRepo userPostRepo;
    private final UserRepository userRepository;

    public ImageServiceImpl(UserPostRepo userPostRepo,UserRepository userRepository) {
        this.userPostRepo = userPostRepo;
        this.userRepository = userRepository;
    }

    @Override
    public void saveImageFile(Long userId, Long postId, MultipartFile file) {
        System.out.println("inside the saveImageFile");
        try{
            System.out.println("inside the saveImageFile");
            User user = userRepository.findById(userId).get();
            System.out.println(user.getUserName());
            Byte[] byteObjects = new Byte[file.getBytes().length];
            int i = 0;
            for(byte b: file.getBytes()){
                byteObjects[i++] =b;
            }
            System.out.println(postId);
            UserPosts userPost = user.getUserPost(postId);
            userPost.setImage(byteObjects);
            System.out.println(userPost.getId());
            user.updateUserPost(userPost);
            userRepository.save(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
