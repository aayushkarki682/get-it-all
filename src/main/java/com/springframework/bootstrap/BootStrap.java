package com.springframework.bootstrap;

import com.springframework.domain.Comments;
import com.springframework.domain.User;
import com.springframework.domain.UserPosts;
import com.springframework.repository.UserPostRepo;
import com.springframework.services.CommentService;
import com.springframework.services.UserPostService;
import com.springframework.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

    private final UserService userService;
    private final UserPostService userPostService;
    private final CommentService commentService;
    private final UserPostRepo userPostRepo;

    public BootStrap(UserService userService, UserPostService userPostService, CommentService commentService, UserPostRepo userPostRepo) {
        this.userService = userService;
        this.userPostService = userPostService;
        this.commentService = commentService;
        this.userPostRepo = userPostRepo;
    }

    @Override
    public void run(String... args) throws Exception {
       UserPosts userPost1 = UserPosts.builder().post("Aayush Karki first post").build();
        Comments comments1 = Comments.builder().comment("That was amazing").build();
        Comments comments2 = Comments.builder().comment("That is amazing again").build();
       // commentService.save(comments1);
        comments1.setUserPosts(userPost1);
        userPost1.getComments().add(comments1);
        comments2.setUserPosts(userPost1);
        userPost1.getComments().add(comments2);
        User user1 = User.builder().firstName("Aayush").lastName("Karki").userName("aaykar").email("aak@gmail.com")
                        .password("kkkkk").build();

        User user2 = User.builder().firstName("Laxman").lastName("GC").userName("laxgc").email("lax@gmail.com")
                          .password("putiiiiii").build();
        User user3 = User.builder().firstName("Sabina").lastName("Karki").userName("sabkar").email("sobu@gmail.com")
                .password("iloveuaayush").build();
        userPost1.setUser(user1);

        user1.getUserPosts().add(userPost1);


        userService.save(user1);
        UserPosts userPost2 = UserPosts.builder().post("Aayush Karki second post").build();
        userPost2.setUser(user2);
        user1.getUserPosts().add(userPost2);

        userService.save(user2);
        userService.save(user3);



        System.out.println("Database initialized with mock datas");
        System.out.println("Total number of users added: "+ userService.count());

    }
}
