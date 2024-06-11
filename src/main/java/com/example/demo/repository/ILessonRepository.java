package com.example.demo.repository;

import com.example.demo.entities.Lesson;
import com.example.demo.exceptions.RepositoryException;

import java.util.List;

public interface ILessonRepository {
    long addLesson(Lesson Lesson) throws RepositoryException;

    void editLesson(Lesson Lesson) throws RepositoryException;

    void deleteLesson(long id) throws RepositoryException;

    Lesson getLessonById(long id) throws RepositoryException;

    List<Lesson> getLesson() throws RepositoryException;
}
