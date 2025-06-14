package com.example.jwt.controller;

import com.example.jwt.entity.User;
import com.example.jwt.service.UserService;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping({"/registerUser"})
    public User registerUser(User user) {
        return userService.registerUser(user);

    }

    @GetMapping({"/forAdmin"})
    public String forAdmin() {
        return "This URL is only accessible to admin .";
    }

    @GetMapping({"/forUser"})
    public String forUser(){
        return "This URL is only accessible to user .";
    }

}
