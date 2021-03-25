package com.springframework.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserPosts extends BaseEntity{


    private String post;
    @Lob
    private Byte[] image;
    private int likePressed;

    @Builder
    public UserPosts(Long id, String post, Byte[] image, int likePressed) {
        super(id);
        this.post = post;
        this.image = image;
        this.likePressed = likePressed;
    }

    @ManyToOne
    private User user;

    public int addUserLike(){
        return this.likePressed++;
    }
}
