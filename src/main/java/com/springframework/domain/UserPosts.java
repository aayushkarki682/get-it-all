package com.springframework.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


    public UserPosts(Long id, String post, Byte[] image) {
        super(id);
        this.post = post;
        this.image = image;
    }

    @ManyToOne
    private User user;
}
