package com.springframework.bootstrap;

import com.springframework.domain.User;
import com.springframework.domain.UserPosts;
import com.springframework.repository.UserPostRepo;
import com.springframework.services.UserPostService;
import com.springframework.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

    private final UserService userService;
    private final UserPostService userPostService;
    private final UserPostRepo userPostRepo;

    public BootStrap(UserService userService, UserPostService userPostService, UserPostRepo userPostRepo) {
        this.userService = userService;
        this.userPostService = userPostService;
        this.userPostRepo = userPostRepo;
    }

    @Override
    public void run(String... args) throws Exception {
       UserPosts userPost1 = UserPosts.builder().post("Aayush Karki").build();


        User user1 = User.builder().firstName("Aayush").lastName("Karki").userName("aaykar").email("aak@gmail.com")
                        .password("kkkkk").build();

        User user2 = User.builder().firstName("Laxman").lastName("GC").userName("laxgc").email("lax@gmail.com")
                          .password("putiiiiii").build();
        User user3 = User.builder().firstName("Sabina").lastName("Karki").userName("sabkar").email("sobu@gmail.com")
                .password("iloveuaayush").build();
        userPost1.setUser(user1);
        user1.getUserPosts().add(userPost1);


        userService.save(user1);
//        userPostService.saveUserPost(user1.getId(), userPost1);
      //  userPostService.saveUserPost(user1.getId(), userPost1);
        userService.save(user2);
        userService.save(user3);



        System.out.println("Database initialized with mock datas");
        System.out.println("Total number of users added: "+ userService.count());

    }
}
