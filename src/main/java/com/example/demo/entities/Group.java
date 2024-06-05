package com.example.demo.entities;

import java.util.Objects;

public class Group {
    private String name;
    private long groupId;

    public Group(long groupId, String name) {
        this.name = name;
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public long getGroupId() {
        return groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return groupId == group.groupId && Objects.equals(name, group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, groupId);
    }
}
