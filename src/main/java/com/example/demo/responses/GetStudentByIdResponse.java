package com.example.demo.responses;

import java.util.Objects;

public class GetStudentByIdResponse {
    private String lastname;
    private String firstname;
    private String patronymic;
    private long group_id;
    private long id;
    private String status;

    public GetStudentByIdResponse(String lastname, String firstname, String patronymic, long group_id, long id, String status) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.group_id = group_id;
        this.id = id;
        this.status = status;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public long getGroup_id() {
        return group_id;
    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetStudentByIdResponse that = (GetStudentByIdResponse) o;
        return group_id == that.group_id && id == that.id && Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(patronymic, that.patronymic) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, patronymic, group_id, id, status);
    }

    @Override
    public String toString() {
        return "GetStudentByIdResponse{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", group_id=" + group_id +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
