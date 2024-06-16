package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.demo.configurations.MyUserDetails;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        return user.map(MyUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
