package com.springframework.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String password;

    @Builder
    public User(Long id, String firstName, String lastName, String userName, String email, String password) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }
}
