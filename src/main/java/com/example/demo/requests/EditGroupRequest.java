package com.example.demo.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class EditGroupRequest {

     @NotNull
     @Length(min = 1, max = 45)
     private String name;

     @NotNull
     private long id;

    public EditGroupRequest() {
    }

    public EditGroupRequest(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }
}
