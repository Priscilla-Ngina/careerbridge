package com.careerBridge.careerBridge.service;

import com.careerBridge.careerBridge.entity.Role;
import com.careerBridge.careerBridge.entity.User;
import com.careerBridge.careerBridge.repository.UserRepository;
import com.careerBridge.careerBridge.dto.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(RegistrationRequest request){

        User user =new User();

        if(request.getRole() == Role.ADMIN){
            throw new IllegalArgumentException("Users cannot register as ADMIN");
        }

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        return userRepository.save(user);

    }
}
