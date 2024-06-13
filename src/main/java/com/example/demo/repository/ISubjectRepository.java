package com.example.demo.repository;

import com.example.demo.entities.Subject;
import com.example.demo.exceptions.RepositoryException;

import java.util.List;

public interface ISubjectRepository {
    long addSubject(Subject subject) throws RepositoryException;

    void editSubject(Subject subject) throws RepositoryException;

    void deleteSubject(long id) throws RepositoryException;

    Subject getSubjectById(long id) throws RepositoryException;

    List<Subject> getSubjects() throws RepositoryException;
}
