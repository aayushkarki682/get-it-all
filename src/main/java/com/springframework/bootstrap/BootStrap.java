package com.springframework.bootstrap;

import com.springframework.domain.*;
import com.springframework.repository.AuthorityRepository;
import com.springframework.repository.RoleRepository;
import com.springframework.repository.UserPostRepo;
import com.springframework.repository.UserRepository;
import com.springframework.services.CommentService;
import com.springframework.services.UserPostService;
import com.springframework.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@Component
public class BootStrap implements CommandLineRunner {

    private final UserService userService;
    private final UserPostService userPostService;
    private final CommentService commentService;
    private final UserPostRepo userPostRepo;
    private final AuthorityRepository authorityRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Transactional
    @Override
    public void run(String... args) throws Exception {
        //userpost auths
        Authority createPost =authorityRepository.save(Authority.builder().permission("UserPosts.create").build());
        Authority updatePost =authorityRepository.save(Authority.builder().permission("UserPosts.update").build());
        Authority deletePost =authorityRepository.save(Authority.builder().permission("UserPosts.delete").build());
        Authority readPost =authorityRepository.save(Authority.builder().permission("UserPosts.read").build());

        Role adminRole = roleRepository.save(Role.builder().name("ADMIN").build());
        Role userRole = roleRepository.save(Role.builder().name("USER").build());

        adminRole.setAuthorities(new HashSet<>(Set.of(createPost, updatePost, deletePost, readPost)));
        userRole.setAuthorities(new HashSet<>(Set.of(createPost, readPost)));

        roleRepository.saveAll(Arrays.asList(adminRole, userRole));


       UserPosts userPost1 = UserPosts.builder().post("Aayush Karki first post").build();
        Comments comments1 = Comments.builder().comment("That was amazing").build();
        Comments comments2 = Comments.builder().comment("That is amazing again").build();
       // commentService.save(comments1);
        comments1.setUserPosts(userPost1);
        userPost1.getComments().add(comments1);
        comments2.setUserPosts(userPost1);
        userPost1.getComments().add(comments2);
        User user1 = User.builder()
                    .firstName("Aayush")
                    .lastName("Karki")
                    .userName("aaykar")
                    .email("aak@gmail.com")
                    .role(adminRole)
                    .password(passwordEncoder.encode("kkkkk"))
                    .build();

        User user2 = User.builder()
                    .firstName("Laxman")
                    .lastName("GC")
                    .userName("laxgc")
                    .email("lax@gmail.com")
                    .role(adminRole)
                    .password(passwordEncoder.encode("kkkkk"))
                    .build();

        User user3 = User.builder()
                    .firstName("Sabina")
                    .lastName("Karki")
                    .userName("sabkar")
                    .email("sobu@gmail.com")
                    .role(adminRole)
                    .password(passwordEncoder.encode("kkkkk")).build();
        userPost1.setUser(user1);

        user1.setUserPosts(new HashSet<>(Set.of(userPost1)));


        userRepository.save(user1);
        UserPosts userPost2 = UserPosts.builder().post("Aayush Karki second post").build();
        userPost2.setUser(user2);
        user1.getUserPosts().add(userPost2);

        userService.save(user2);
        userService.save(user3);



        System.out.println("Database initialized with mock datas");
        System.out.println("Total number of users added: "+ userService.count());

    }
}
