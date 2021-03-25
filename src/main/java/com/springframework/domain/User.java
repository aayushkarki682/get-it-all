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
@Table(name = "user")
public class User extends BaseEntity {



    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

    @Builder
    public User(Long id, String firstName, String lastName, String userName, String email, String password, UserPosts userPosts) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "user", fetch= FetchType.EAGER)
    private Set<UserPosts> userPosts = new HashSet<>();



    public UserPosts getUserPost(Long userPostId){
        for(UserPosts u : userPosts){
         //   System.out.println("getting userpost id from getuser post" + u.getId());
            if(u.getId() == userPostId){
              //  System.out.println("id matched");
                return u;
            }
        }
        return new UserPosts();
    }

    public void updateUserPost(UserPosts userP){
        for(UserPosts u : userPosts){
            if(userP.getId() == u.getId()){
                u = userP;
            }
        }
    }
}
