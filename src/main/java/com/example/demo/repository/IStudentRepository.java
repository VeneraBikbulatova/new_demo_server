package com.example.demo.repository;


import com.example.demo.entities.Student;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.requests.AddStudentRequest;
import com.example.demo.responses.GetLessonByIdResponse;
import com.example.demo.responses.GetStudentByIdResponse;

import java.util.List;

public interface IStudentRepository {
    long addStudent(AddStudentRequest addStudentRequest) throws RepositoryException;

    void editStudent(Student student) throws RepositoryException;

    void deleteStudent(long id) throws RepositoryException;

    Student getStudentById(long id) throws RepositoryException;

    List<Student> getStudents() throws RepositoryException;



}
