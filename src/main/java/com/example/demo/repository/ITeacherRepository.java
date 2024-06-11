package com.example.demo.repository;

import com.example.demo.entities.Teacher;
import com.example.demo.exceptions.RepositoryException;

import java.util.List;

public interface ITeacherRepository {
    long addTeacher(Teacher teacher) throws RepositoryException;

    void editTeacher(Teacher teacher) throws RepositoryException;

    void deleteTeacher(long id) throws RepositoryException;

    Teacher getTeacherById(long id) throws RepositoryException;

    List<Teacher> getTeacher() throws RepositoryException;

}
