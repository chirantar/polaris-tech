package com.example.polaris.controllers;

import com.example.polaris.models.User;
import com.example.polaris.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String home(){
        return "Success";
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User userJson){//        System.out.println(user.getUserName());
        System.out.println("Raw JSON: " + userJson);
        User createdUser = userService.addUser(userJson);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}
