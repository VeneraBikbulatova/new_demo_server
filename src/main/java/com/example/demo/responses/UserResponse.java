package com.example.demo.responses;

import com.example.demo.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private long userId;

    public UserResponse(User user){
        this.userId = user.getUser_id();
    }
}