package com.springframework.domain;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_posts")
public class UserPosts extends BaseEntity{


    private String post;
    @Lob
    private Byte[] image;
    private int likePressed;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH},
                mappedBy = "userPosts", fetch = FetchType.EAGER)
    private Set<Comments> comments = new HashSet<>();

    @Builder
    public UserPosts(Long id, String post, Byte[] image, int likePressed) {
        super(id);
        this.post = post;
        this.image = image;
        this.likePressed = likePressed;
    }

    @ManyToOne
    private User user;

    public Comments getComment(Long commentId){
        for(Comments c: comments){
            if(c.getId() == commentId){
                return c;
            }
        }
        return null;
    }

    public int addUserLike(){
        return this.likePressed++;
    }
}
