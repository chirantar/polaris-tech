package com.example.polaris.services;

import com.example.polaris.controllers.UserController;
import com.example.polaris.models.User;
import com.example.polaris.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user){
        return userRepository.save(user);
    }
}
