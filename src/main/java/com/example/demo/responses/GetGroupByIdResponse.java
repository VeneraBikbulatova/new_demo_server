package com.example.demo.responses;

import java.util.Objects;

public class GetGroupByIdResponse {
    private String name;
    private long id;

    public GetGroupByIdResponse(String name, long id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetGroupByIdResponse that = (GetGroupByIdResponse) o;
        return id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "GroupResponse{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
