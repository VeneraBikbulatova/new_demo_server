package com.example.demo.entities;

import lombok.Data;

import java.util.Objects;

@Data
public class Attendance {
    private long id;
    private long lesson_id;
    private long student_id;

    public Attendance(long lesson_id, long student_id, long id) {
        this.lesson_id = lesson_id;
        this.student_id = student_id;
        this.id = id;
    }

    public long getLesson_id() {
        return lesson_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attendance that = (Attendance) o;
        return id == that.id && lesson_id == that.lesson_id && student_id == that.student_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lesson_id, student_id);
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", lesson_id=" + lesson_id +
                ", student_id=" + student_id +
                '}';
    }
}
