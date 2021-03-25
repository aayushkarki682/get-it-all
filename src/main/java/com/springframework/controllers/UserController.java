package com.springframework.controllers;

import com.springframework.domain.User;
import com.springframework.domain.UserPosts;
import com.springframework.services.ImageService;
import com.springframework.services.UserPostService;
import com.springframework.services.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collection;

@Controller
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;
    private final UserPostService userPostService;
    private final ImageService imageService;



    public UserController(UserService userService, UserPostService userPostService, ImageService imageService) {
        this.userService = userService;
        this.userPostService = userPostService;
        this.imageService = imageService;
    }

    private Long loggedUserId = 0L;
    private User loggedUser = new User();
    private UserPosts userPosts = new UserPosts();

    @ModelAttribute("allUserPosts")
    public Collection<UserPosts> populateWithUserPosts(){
        return userPostService.findAllUserPosts();
    }

    @InitBinder("userpost")
    public void initOwnerBinder(WebDataBinder webDataBinder){
        webDataBinder.setDisallowedFields("id");
    }

    @GetMapping
    public String getIndexPage(Model model){
        System.out.println(loggedUserId+"after login");
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("loggedUserId", loggedUserId);
        model.addAttribute("userpost", userPosts);


      //  model.addAttribute("allPosts", );  todo add way to pass all images for the user
        System.out.println("User post id from index page after the post was submitted + "+userPosts.getId());
        return "index";
    }

    @GetMapping("/signUp")
    public String getSignUpPage(Model model){
        model.addAttribute("user", new User());
        return "signUp";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/signUpSuccess")
    public String saveNewCustomer(@ModelAttribute User user, Model model){
        System.out.println(user.getEmail());
        User savedUser = userService.save(user);
        loggedUser = user;
        loggedUserId = savedUser.getId();

        return "redirect:/user/";
    }

    @PostMapping("/loginSuccess")
    public String customerLoggedIn(@ModelAttribute("user") User user, Model model){




        User retrievedUser = userService.checkLoginInfo(user);
        if(retrievedUser != null){
            loggedUserId = retrievedUser.getId();
            loggedUser = retrievedUser;
            return "redirect:/user/";
        } else{
            System.out.println("Invalid username");
            return "redirect:/user/login";
        }
    }

    @GetMapping("/{id}/createPost")
    public String createNewPost(@PathVariable Long id, Model model){
        model.addAttribute("id", id);
        model.addAttribute("userpost", userPosts);
        System.out.println("Inside the image upload form + "+userPosts.getId());
        return "imageUploadForm";

    }

    @PostMapping("/{id}/createPostSuccess")
    public String createPostSuccess(@PathVariable Long id, @ModelAttribute("userpost") UserPosts newUserPosts){
        User newUser = userService.findById(id);
        System.out.println(newUser.getFirstName());
        newUserPosts.setUser(newUser);
        newUser.getUserPosts().add(newUserPosts);

        UserPosts savedUserPost = userPostService.save(newUserPosts);
//        userPosts.setId(savedUserPost.getId());
//        userPosts.setUser(savedUserPost.getUser());
//        userPosts.setPost(savedUserPost.getPost());
//        userPosts.setImage(savedUserPost.getImage());
//        loggedUser.getUserPosts().add(newUserPosts);
        userPosts = savedUserPost;
        loggedUser = newUser;
      //  userPostService.saveUserPost(id, newUserPosts);
        return "redirect:/user/";
    }

    @PostMapping("/{id}/{postId}/imagePostSuccess")
    public String imagePostSuccess(@PathVariable("id") Long id, @PathVariable("postId") Long postId,
                                   @RequestParam("imagefile") MultipartFile file){
        System.out.println(postId + "from controller");
        imageService.saveImageFile(id,postId,  file);
        return "redirect:/user/";
    }

    @GetMapping("/{userId}/{postId}/userPostImage")
    public void renderImageFromDB(@PathVariable("userId") Long userId, @PathVariable("postId") Long postId,
                                  HttpServletResponse response) throws Exception{
        UserPosts userPosts = userService.findUserPostById(userId, postId);
        if(userPosts.getImage() != null){
            byte[] byteArray = new byte[userPosts.getImage().length];
            int i = 0;
            for (Byte wrappedByte : userPosts.getImage()){
                byteArray[i++] =wrappedByte;
            }
            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }


}
