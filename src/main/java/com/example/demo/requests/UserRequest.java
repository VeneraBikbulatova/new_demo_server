package com.example.demo.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    @NotBlank
    private String password;
    @NotBlank
    private String role;
    @NotBlank
    private String user_name;

}