package com.example.demo.responses;

import java.util.Objects;

public class GetTeacherByIdResponse {
    private long id;
    private String firstname;
    private String patronymic;
    private String lastname;
    private long subject_id;

    public GetTeacherByIdResponse(long id, String firstname, String patronymic, String lastname, long subject_id) {
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
        GetTeacherByIdResponse that = (GetTeacherByIdResponse) o;
        return id == that.id && subject_id == that.subject_id && Objects.equals(firstname, that.firstname) && Objects.equals(patronymic, that.patronymic) && Objects.equals(lastname, that.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, patronymic, lastname, subject_id);
    }

    @Override
    public String toString() {
        return "GetTeacherByIdResponse{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", lastname='" + lastname + '\'' +
                ", subject_id=" + subject_id +
                '}';
    }
}
