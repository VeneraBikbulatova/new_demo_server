package com.example.demo.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;
import java.util.Objects;

public class AddLessonRequest {
    @NotNull
    @Length(min = 1, max = 45)
    private String name;

    @NotNull
    private long group_id;

    @NotNull
    private long teacher_id;

    @NotNull
    private long subject_id;

    @NotBlank
    @Pattern(regexp = "\\d{2}\\.\\d{2}\\.\\d{4}")
    private Date lesson_date;

    @NotNull
    private int lesson_time;

    public AddLessonRequest(String name, long group_id, long teacher_id, long subject_id, Date lesson_date, int lesson_time) {
        this.name = name;
        this.group_id = group_id;
        this.teacher_id = teacher_id;
        this.subject_id = subject_id;
        this.lesson_date = lesson_date;
        this.lesson_time = lesson_time;
    }

    public AddLessonRequest() {}

    public String getName() {
        return name;
    }

    public long getGroup_id() {
        return group_id;
    }

    public long getTeacher_id() {
        return teacher_id;
    }

    public long getSubject_id() {
        return subject_id;
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
        AddLessonRequest that = (AddLessonRequest) o;
        return group_id == that.group_id && teacher_id == that.teacher_id && subject_id == that.subject_id && lesson_time == that.lesson_time && Objects.equals(name, that.name) && Objects.equals(lesson_date, that.lesson_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, group_id, teacher_id, subject_id, lesson_date, lesson_time);
    }

    @Override
    public String toString() {
        return "AddLessonRequest{" +
                "name='" + name + '\'' +
                ", group_id=" + group_id +
                ", teacher_id=" + teacher_id +
                ", subject_id=" + subject_id +
                ", lesson_date=" + lesson_date +
                ", lesson_time=" + lesson_time +
                '}';
    }
}
