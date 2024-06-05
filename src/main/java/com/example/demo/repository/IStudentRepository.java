package com.example.demo.repository;


import com.example.demo.entities.Student;
import com.example.demo.exceptions.RepositoryException;

import java.util.List;

public interface IStudentRepository {
    long addStudent(Student student) throws RepositoryException;

    void editStudent(Student student) throws RepositoryException;

    void deleteStudent(long id) throws RepositoryException;

    Student getStudentById(long id) throws RepositoryException;

    List<Student> getStudents() throws RepositoryException;



}
