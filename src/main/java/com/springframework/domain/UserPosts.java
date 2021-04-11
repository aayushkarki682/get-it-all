package com.springframework.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_posts")
public class UserPosts implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String post;
    @Lob
    private Byte[] image;
    private int likePressed;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH},
                mappedBy = "userPosts", fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Comments> comments = new HashSet<>();

    @Builder
    public UserPosts(Long id, String post, Byte[] image, int likePressed) {
        this.id=id;
        this.post = post;
        this.image = image;
        this.likePressed = likePressed;
    }

    @ManyToOne
    @JsonBackReference
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
