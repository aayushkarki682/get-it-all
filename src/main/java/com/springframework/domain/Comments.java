package com.springframework.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comments extends BaseEntity {

    private String comment;

    @Builder
    public Comments(Long id, String comment) {
        super(id);
        this.comment = comment;
    }

    @ManyToOne
    private UserPosts userPosts;

//    @ManyToOne
//    private User user;

}
