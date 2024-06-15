package com.example.demo.responses;

import java.util.Objects;

public class GetStudentByIdResponse {
    private String lastname;
    private String firstname;
    private String patronymic;
    private String group_name;
    private long id;
//    private long group_id;
    private String status;

    public GetStudentByIdResponse(String lastname, String firstname, String patronymic, String group_name, long id, String status) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.patronymic = patronymic;
        this.group_name = group_name;
        this.id = id;
        this.status = status;
    }

//    public GetStudentByIdResponse(String lastname, String firstname, String patronymic, long group_id, long id, String status) {
//        this.lastname = lastname;
//        this.firstname = firstname;
//        this.patronymic = patronymic;
//        this.id = id;
//        this.group_id = group_id;
//        this.status = status;
//    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getGroup_name() {
        return group_name;
    }

//    public long getGroup_id(){
//        return group_id;
//    }

    public long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

//    public void setGroup_id(long group_id) {
//        this.group_id = group_id;
//    }
    public void setId(long id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetStudentByIdResponse that = (GetStudentByIdResponse) o;
        return id == that.id && Objects.equals(lastname, that.lastname) && Objects.equals(firstname, that.firstname) && Objects.equals(patronymic, that.patronymic) && Objects.equals(group_name, that.group_name) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastname, firstname, patronymic, group_name, id, status);
    }

    @Override
    public String toString() {
        return "GetStudentByIdResponse{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", group_name='" + group_name + '\'' +
                ", id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
