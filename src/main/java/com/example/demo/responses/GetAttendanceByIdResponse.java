package com.example.demo.responses;

import java.util.Objects;

public class GetAttendanceByIdResponse {
    private long id;
    private String lesson_name;
    private String student_firstname;
    private String student_patronymic;
    private String student_lastname;

    public GetAttendanceByIdResponse(long id, String lesson_name, String student_firstname, String student_patronymic, String student_lastname) {
        this.id = id;
        this.lesson_name = lesson_name;
        this.student_firstname = student_firstname;
        this.student_patronymic = student_patronymic;
        this.student_lastname = student_lastname;
    }

    public long getId() {
        return id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public String getStudent_firstname() {
        return student_firstname;
    }

    public String getStudent_patronymic() {
        return student_patronymic;
    }

    public String getStudent_lastname() {
        return student_lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAttendanceByIdResponse that = (GetAttendanceByIdResponse) o;
        return id == that.id && Objects.equals(lesson_name, that.lesson_name) && Objects.equals(student_firstname, that.student_firstname) && Objects.equals(student_patronymic, that.student_patronymic) && Objects.equals(student_lastname, that.student_lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lesson_name, student_firstname, student_patronymic, student_lastname);
    }

    @Override
    public String toString() {
        return "GetAttendanceByIdResponse{" +
                "id=" + id +
                ", lesson_name='" + lesson_name + '\'' +
                ", student_firstname='" + student_firstname + '\'' +
                ", student_patronymic='" + student_patronymic + '\'' +
                ", student_lastname='" + student_lastname + '\'' +
                '}';
    }
}
