package com.example.demo.requests;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class EditSubjectRequest {
    @NotNull
    private long id;
    @NotNull
    @Length(min = 1, max = 45)
    private String name;

    public EditSubjectRequest(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
