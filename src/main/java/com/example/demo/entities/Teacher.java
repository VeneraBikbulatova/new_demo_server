package com.example.demo.entities;

import lombok.Data;

import java.util.Objects;

@Data
public class Teacher {
    private long id;
    private String firstname;
    private String patronymic;
    private String lastname;
    private long subject_id;

    public Teacher(long id, String firstname, String patronymic, String lastname, long subject_id) {
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
        Teacher teacher = (Teacher) o;
        return id == teacher.id && subject_id == teacher.subject_id && Objects.equals(firstname, teacher.firstname) && Objects.equals(patronymic, teacher.patronymic) && Objects.equals(lastname, teacher.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, patronymic, lastname, subject_id);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", lastname='" + lastname + '\'' +
                ", subject_id=" + subject_id +
                '}';
    }
}
