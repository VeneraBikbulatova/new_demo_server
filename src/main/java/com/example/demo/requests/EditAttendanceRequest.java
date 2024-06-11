package com.example.demo.requests;

import jakarta.validation.constraints.NotNull;

public class EditAttendanceRequest {
    @NotNull
    private long id;
    @NotNull
    private long lesson_id;
    @NotNull
    private long student_id;

    public EditAttendanceRequest(long id, long lesson_id, long student_id) {
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
}
