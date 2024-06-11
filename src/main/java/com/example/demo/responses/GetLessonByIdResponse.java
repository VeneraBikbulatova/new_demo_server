package com.example.demo.responses;

import java.sql.Date;
import java.util.Objects;

public class GetLessonByIdResponse {
    private long id;
    private String name;
    private long group_id;
    private long teacher_id;
    private long subject_id;
    private Date lesson_date;
    private int lesson_time;

    public GetLessonByIdResponse(long id, String name, long group_id, long teacher_id, long subject_id, Date lesson_date, int lesson_time) {
        this.id = id;
        this.name = name;
        this.group_id = group_id;
        this.teacher_id = teacher_id;
        this.subject_id = subject_id;
        this.lesson_date = lesson_date;
        this.lesson_time = lesson_time;
    }

    public long getId() {
        return id;
    }

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
        GetLessonByIdResponse that = (GetLessonByIdResponse) o;
        return id == that.id && group_id == that.group_id && teacher_id == that.teacher_id && subject_id == that.subject_id && lesson_time == that.lesson_time && Objects.equals(name, that.name) && Objects.equals(lesson_date, that.lesson_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, group_id, teacher_id, subject_id, lesson_date, lesson_time);
    }

    @Override
    public String toString() {
        return "GetLessonByIdResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", group_id=" + group_id +
                ", teacher_id=" + teacher_id +
                ", subject_id=" + subject_id +
                ", lesson_date=" + lesson_date +
                ", lesson_time=" + lesson_time +
                '}';
    }
}
