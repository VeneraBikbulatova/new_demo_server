package com.example.demo.requests;

public class EditGroupRequest {
     private String name;
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
