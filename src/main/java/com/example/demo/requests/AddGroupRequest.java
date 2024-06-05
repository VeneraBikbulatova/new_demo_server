package com.example.demo.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddGroupRequest {
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
