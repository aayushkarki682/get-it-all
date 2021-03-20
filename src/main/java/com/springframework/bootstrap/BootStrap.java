package com.springframework.bootstrap;

import com.springframework.domain.User;
import com.springframework.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {

    private final UserService userService;

    public BootStrap(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user1 = User.builder().firstName("Aayush").lastName("Karki").userName("aaykar").email("aak@gmail.com")
                        .password("kkkkk").build();
        User user2 = User.builder().firstName("Laxman").lastName("GC").userName("laxgc").email("lax@gmail.com")
                          .password("putiiiiii").build();
        User user3 = User.builder().firstName("Sabina").lastName("Karki").userName("sabkar").email("sobu@gmail.com")
                .password("iloveuaayush").build();

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);

        System.out.println("Database initialized with mock datas");
        System.out.println("Total number of users added: "+ userService.count());

    }
}
