package com.example.demo.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class AddAttendanceRequest {
    @NotNull
    private long lesson_id;

    @NotNull
    private long student_id;

    @JsonCreator
    public AddAttendanceRequest(
            @JsonProperty long lesson_id,
            @JsonProperty long student_id) {
        this.lesson_id = lesson_id;
        this.student_id = student_id;
    }

    public long getLesson_id() {
        return lesson_id;
    }

    public long getStudent_id() {
        return student_id;
    }
}
