package com.example.demo.responses;

import java.util.Objects;

public class GetSubjectByIdResponse {
    private long id;
    private String name;

    public GetSubjectByIdResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetSubjectByIdResponse that = (GetSubjectByIdResponse) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "GetSubjectByIdResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
