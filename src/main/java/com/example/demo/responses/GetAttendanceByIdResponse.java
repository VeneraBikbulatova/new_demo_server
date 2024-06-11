package com.example.demo.responses;

import java.util.Objects;

public class GetAttendanceByIdResponse {
    private long id;
    private long lesson_id;
    private long student_id;

    public GetAttendanceByIdResponse(long id, long lesson_id, long student_id) {
        this.id = id;
        this.lesson_id = lesson_id;
        this.student_id = student_id;
    }

    public long getId() {
        return id;
    }

    public long getLesson_id() {
        return lesson_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetAttendanceByIdResponse that = (GetAttendanceByIdResponse) o;
        return id == that.id && lesson_id == that.lesson_id && student_id == that.student_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lesson_id, student_id);
    }

    @Override
    public String toString() {
        return "GetAttendanceByIdResponce{" +
                "id=" + id +
                ", lesson_id=" + lesson_id +
                ", student_id=" + student_id +
                '}';
    }
}
