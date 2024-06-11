package com.example.demo.requests;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

public class EditTeacherRequest {
    @NotNull
    private long id;

    @NotNull
    @Length(min = 1, max = 45)
    private String firstname;

    @NotNull
    @Length(min = 1, max = 45)
    private String patronymic;

    @NotNull
    @Length(min = 1, max = 45)
    private String lastname;

    @NotNull
    private long subject_id;

    public EditTeacherRequest(long id, String firstname, String patronymic, String lastname, long subject_id) {
        this.id = id;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.lastname = lastname;
        this.subject_id = subject_id;
    }

    public long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getLastname() {
        return lastname;
    }

    public long getSubject_id() {
        return subject_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EditTeacherRequest that = (EditTeacherRequest) o;
        return id == that.id && subject_id == that.subject_id && Objects.equals(firstname, that.firstname) && Objects.equals(patronymic, that.patronymic) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, patronymic, lastname, subject_id);
    }

    @Override
    public String toString() {
        return "EditTeacherRequest{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", lastname='" + lastname + '\'' +
                ", subject_id=" + subject_id +
                '}';
    }
}
