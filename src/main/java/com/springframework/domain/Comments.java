package com.springframework.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comments implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @Builder
    public Comments(Long id, String comment) {
        this.id=id;
        this.comment = comment;
    }

    @ManyToOne
    @JsonBackReference
    private UserPosts userPosts;

//    @ManyToOne
//    private User user;

}
