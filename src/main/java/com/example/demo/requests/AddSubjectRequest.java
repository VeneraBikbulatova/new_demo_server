package com.example.demo.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AddSubjectRequest {
    @NotNull
    @Length(min = 1, max = 45)
    private String name;

    @JsonCreator
    public AddSubjectRequest(
            @JsonProperty String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
