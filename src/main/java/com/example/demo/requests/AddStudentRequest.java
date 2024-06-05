package com.example.demo.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class AddStudentRequest {
    private String firstname;
    private String lastname;
    private String patronymic;
    private long group;
    private String status;

    public AddStudentRequest() {}
    public AddStudentRequest(String firstname, String patronymic, String lastname, long group, String status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.group = group;
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
        return group;
    }

    public String getStatus() {
        return status;
    }
}
