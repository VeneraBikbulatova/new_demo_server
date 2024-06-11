package com.example.demo.responses;

import java.util.Objects;

public class AddAttendanceResponse {
    private long id;

    public AddAttendanceResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddAttendanceResponse that = (AddAttendanceResponse) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AddAttendanceResponse{" +
                "id=" + id +
                '}';
    }
}
