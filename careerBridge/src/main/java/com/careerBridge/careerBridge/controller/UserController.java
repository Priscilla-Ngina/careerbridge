package com.careerBridge.careerBridge.controller;

import com.careerBridge.careerBridge.entity.User;
import com.careerBridge.careerBridge.service.UserService;
import com.careerBridge.careerBridge.dto.RegistrationRequest;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User register(@Valid @RequestBody RegistrationRequest registrationRequest) {



        return userService.register(registrationRequest);
    }
}
