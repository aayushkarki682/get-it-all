package com.springframework.controllers;

import com.springframework.domain.User;
import com.springframework.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getIndexPage(){
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
    public String saveNewCustomer(@ModelAttribute User user){
        System.out.println(user.getEmail());
        userService.save(user);
        return "redirect:/user/";
    }

    @PostMapping("/loginSuccess")
    public String customerLoggedIn(@ModelAttribute("user") User user){
        String message = userService.checkLoginInfo(user);
        if(message != null){
            return "redirect:/user/";
        } else{
            System.out.println("Invalid username");
            return "redirect:/user/login";
        }


    }

}
