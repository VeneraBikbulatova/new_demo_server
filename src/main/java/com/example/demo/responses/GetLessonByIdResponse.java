package com.example.demo.responses;

import java.sql.Date;
import java.util.Objects;

public class GetLessonByIdResponse {
    private long id;
    private String name;
    private String group_name;
    private String teacher_firstname;
    private String teacher_patronymic;
    private String teacher_lastname;
    private String subject_name;
    private Date lesson_date;
    private int lesson_time;

    public GetLessonByIdResponse(long id, String name, String group_name, String teacher_firstname, String teacher_patronymic, String teacher_lastname, String subject_name, Date lesson_date, int lesson_time) {
        this.id = id;
        this.name = name;
        this.group_name = group_name;
        this.teacher_firstname = teacher_firstname;
        this.teacher_patronymic = teacher_patronymic;
        this.teacher_lastname = teacher_lastname;
        this.subject_name = subject_name;
        this.lesson_date = lesson_date;
        this.lesson_time = lesson_time;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public String getTeacher_firstname() {
        return teacher_firstname;
    }

    public String getTeacher_patronymic() {
        return teacher_patronymic;
    }

    public String getTeacher_lastname() {
        return teacher_lastname;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public Date getLesson_date() {
        return lesson_date;
    }

    public int getLesson_time() {
        return lesson_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetLessonByIdResponse that = (GetLessonByIdResponse) o;
        return id == that.id && lesson_time == that.lesson_time && Objects.equals(name, that.name) && Objects.equals(group_name, that.group_name) && Objects.equals(teacher_firstname, that.teacher_firstname) && Objects.equals(teacher_patronymic, that.teacher_patronymic) && Objects.equals(teacher_lastname, that.teacher_lastname) && Objects.equals(subject_name, that.subject_name) && Objects.equals(lesson_date, that.lesson_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, group_name, teacher_firstname, teacher_patronymic, teacher_lastname, subject_name, lesson_date, lesson_time);
    }

    @Override
    public String toString() {
        return "GetLessonByIdResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group_name='" + group_name + '\'' +
                ", teacher_firstname='" + teacher_firstname + '\'' +
                ", teacher_patronymic='" + teacher_patronymic + '\'' +
                ", teacher_lastname='" + teacher_lastname + '\'' +
                ", subject_name='" + subject_name + '\'' +
                ", lesson_date=" + lesson_date +
                ", lesson_time=" + lesson_time +
                '}';
    }
}
