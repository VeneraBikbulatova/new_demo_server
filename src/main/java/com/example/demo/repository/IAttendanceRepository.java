package com.example.demo.repository;

import com.example.demo.entities.Attendance;
import com.example.demo.exceptions.RepositoryException;

import java.util.List;

public interface IAttendanceRepository {
    void addAttendance(Attendance attendance) throws RepositoryException;

    void editAttendance(Attendance attendance) throws RepositoryException;

    void deleteAttendance(long id) throws RepositoryException;

    Attendance getAttendanceById(long id) throws RepositoryException;

    List<Attendance> getAttendances() throws RepositoryException;
}
