package com.example.demo.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class EditStudentRequest {

    @NotNull
    @Length(min = 1, max = 45)
    private String firstname;

    @NotNull
    @Length(min = 1, max = 45)
    private String lastname;

    @NotNull
    @Length(min = 1, max = 45)
    private String patronymic;

    @NotBlank
    private long group_id;

    @NotNull
    private long id;

    @NotNull
    @Length(min = 1, max = 45)
    private String status;

    public EditStudentRequest(String firstname, String patronymic, String lastname, long group_id, long id, String status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.group_id = group_id;
        this.id = id;
        this.status = status;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public long getGroupId() {
        return group_id;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
