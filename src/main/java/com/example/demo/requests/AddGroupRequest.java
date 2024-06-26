package com.example.demo.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class AddGroupRequest {

    @NotNull
    @Length(min = 1, max = 45)
    private String name;

    @JsonCreator
    public AddGroupRequest(
            @JsonProperty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
