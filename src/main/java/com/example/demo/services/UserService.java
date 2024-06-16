package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repository.IUserRepository;
import com.example.demo.requests.UserRequest;
import com.example.demo.responses.UserResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponse addUser(@Valid UserRequest request) {
        User user = new User(null, request.getPassword(), request.getRole(), request.getUser_name());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var save = userRepository.save(user);
        return new UserResponse(save.getUser_id());
    }
}