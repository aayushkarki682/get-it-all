package com.springframework.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

    @Builder
    public User(Long id, String firstName, String lastName, String userName, String email, String password, UserPosts userPosts) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.REFRESH},
            mappedBy = "user", fetch= FetchType.EAGER)
    @JsonManagedReference
    private Set<UserPosts> userPosts = new HashSet<>();

    @Singular
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<Role> roles;


    @Transient
    private Set<Authority> authorities;

    public Set<Authority> getAuthorities(){
        return this.roles.stream()
                .map(Role::getAuthorities)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNonExpired = true;

    @Builder.Default
    private Boolean enabled = true;


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

    public int sizeOfPosts(){
        return userPosts.size();
    }


}
