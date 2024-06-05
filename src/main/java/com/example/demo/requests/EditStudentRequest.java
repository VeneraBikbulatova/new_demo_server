package com.example.demo.requests;

public class EditStudentRequest {
    private String firstname;
    private String lastname;
    private String patronymic;
    private long group;
    private long id;
    private String status;

    public EditStudentRequest(String firstname, String patronymic, String lastname, long group, long id, String status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.group = group;
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
        return group;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }
}
