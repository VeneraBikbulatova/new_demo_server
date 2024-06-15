package com.example.demo.repository;

import com.example.demo.entities.Attendance;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.responses.GetAttendanceByIdResponse;
import com.example.demo.responses.GetStudentByIdResponse;

import java.util.List;

public interface IAttendanceRepository {
    long addAttendance(Attendance attendance) throws RepositoryException;

    void editAttendance(Attendance attendance) throws RepositoryException;

    void deleteAttendance(long id) throws RepositoryException;

    Attendance getAttendanceById(long id) throws RepositoryException;

    List<Attendance> getAttendances(long lesson_id) throws RepositoryException;
}
