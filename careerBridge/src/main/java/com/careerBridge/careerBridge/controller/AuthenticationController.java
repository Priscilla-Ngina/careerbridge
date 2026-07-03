package com.careerBridge.careerBridge.controller;

import com.careerBridge.careerBridge.dto.LoginRequest;
import com.careerBridge.careerBridge.security.JwtService;
import com.careerBridge.careerBridge.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.careerBridge.careerBridge.dto.AuthenticationResponse;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest request) {

        return authenticationService.login(request);

    }

    @GetMapping("/test")
    public String test() {
        return "JWT Authentication is working!";
    }

}
