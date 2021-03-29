package com.springframework.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostIdAndComment {
    private Long id;
    private String comment;
}
