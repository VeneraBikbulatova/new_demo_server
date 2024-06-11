package com.example.demo.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
public class AddStudentRequest {

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
    private long group;

    @NotNull
    @Length(min = 1, max = 45)
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
