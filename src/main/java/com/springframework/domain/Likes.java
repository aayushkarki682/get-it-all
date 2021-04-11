package com.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Likes {


    Map<Long, Boolean> userLike = new HashMap<>();

    public void addUserLike(Long userId, boolean val){
        userLike.put(userId, val);
    }


}
