package com.careerBridge.careerBridge.security;

import org.springframework.security.core.context.SecurityContextHolder;
import com.careerBridge.careerBridge.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.careerBridge.careerBridge.entity.User;
import org.springframework.security.core.Authentication;

@Service
public class SecurityService {

    private final UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new IllegalArgumentException("User not found"));
        return user;
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

}
