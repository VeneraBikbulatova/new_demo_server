package com.example.demo.controllers;

import com.example.demo.exceptions.ServiceException;
import com.example.demo.requests.UserRequest;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance_tracker/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/welcome")
    public String welcomePage() {
        return "This is unprotected page";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String pageForUsers() {
        return "This is page only for users";
    }

    @GetMapping("/admins")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String pageForAdmins() {
        return "This is page only for admins";
    }

    @GetMapping("/all")
    public String pageForAll() {
        return "This is page for all users";
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRequest request) throws ServiceException {
        try {
            return new ResponseEntity<>(userService.addUser(request), HttpStatus.OK);
        }catch (Exception e){
            throw new ServiceException("this user already exists");
        }
    }
}
