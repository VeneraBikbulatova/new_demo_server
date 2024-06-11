package com.example.demo.entities;

import lombok.Data;

import java.util.Objects;

@Data
public class Student {
    private String lastname;
    private String firstname;
    private String patronymic;
    private long group_id;
    private long id;
    private String status;

    public Student(String firstname,
                   String patronymic,
                   String lastname,
                   long group_id,
                   long id,
                   String status) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.group_id = group_id;
        this.id = id;
        this.status = status;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public long getGroup() {
        return group_id;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return group_id == student.group_id && id == student.id && Objects.equals(lastname, student.lastname) && Objects.equals(firstname, student.firstname) && Objects.equals(patronymic, student.patronymic) && Objects.equals(status, student.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, patronymic, group_id, id, status);
    }
}

