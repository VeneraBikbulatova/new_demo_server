package com.example.demo.repository;

import com.example.demo.entities.Lesson;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.responses.GetLessonByIdResponse;

import java.util.List;

public interface ILessonRepository {
    long addLesson(Lesson Lesson) throws RepositoryException;

    void editLesson(Lesson Lesson) throws RepositoryException;

    void deleteLesson(long id) throws RepositoryException;

    GetLessonByIdResponse getLessonById(long id) throws RepositoryException;

    List<GetLessonByIdResponse> getLesson() throws RepositoryException;
}
