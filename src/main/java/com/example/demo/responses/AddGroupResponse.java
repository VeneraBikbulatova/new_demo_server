package com.example.demo.responses;

import java.util.Objects;

public class AddGroupResponse {

    long id;

    public AddGroupResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddGroupResponse that = (AddGroupResponse) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AddGroupResponse{" +
                "id=" + id +
                '}';
    }
}
