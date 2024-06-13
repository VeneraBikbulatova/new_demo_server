package com.example.demo.services;

import com.example.demo.entities.Lesson;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.repository.ILessonRepository;
import com.example.demo.repository.ILessonRepository;
import com.example.demo.repository.LessonRepository;
import com.example.demo.requests.AddLessonRequest;
import com.example.demo.requests.AddLessonRequest;
import com.example.demo.requests.EditLessonRequest;
import com.example.demo.responses.GetLessonByIdResponse;

import java.util.List;

public class LessonService {

    private ILessonRepository lessonRepository;


    public LessonService(ILessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public long addLesson(AddLessonRequest addLessonRequest) throws ServiceException {
        try{
            Lesson lesson = new Lesson(
                    0,
                    addLessonRequest.getName(),
                    addLessonRequest.getGroup_id(),
                    addLessonRequest.getTeacher_id(),
                    addLessonRequest.getSubject_id(),
                    addLessonRequest.getLesson_date(),
                    addLessonRequest.getLesson_time());
            lessonRepository.addLesson(lesson);
            return lesson.getId();
        } catch (RepositoryException r) {
            throw new ServiceException("service error in addLesson name=" + addLessonRequest.getName(), r);
        }
    }

    public long editLesson(EditLessonRequest editLessonRequest) throws ServiceException{
        try{
            Lesson lesson = new Lesson(
                    editLessonRequest.getId(),
                    editLessonRequest.getName(),
                    editLessonRequest.getGroup_id(),
                    editLessonRequest.getTeacher_id(),
                    editLessonRequest.getSubject_id(),
                    editLessonRequest.getLesson_date(),
                    editLessonRequest.getLesson_time()
            );
            lessonRepository.editLesson(lesson);
            return lesson.getId();
        } catch (RepositoryException r){
            throw new ServiceException("service error in editLesson id=" + editLessonRequest.getId(), r);
        }
    }

    public GetLessonByIdResponse getLessonById(long id) throws ServiceException {
        try {
            return lessonRepository.getLessonById(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in getLessonById id=" + id, r);
        }
    }

    public List<GetLessonByIdResponse> getAllLessons() throws ServiceException{
        try{
            return lessonRepository.getLesson();
        } catch (RepositoryException r){
            throw new ServiceException("service error in getAllLessons");
        }
    }

    public void deleteLesson(long id) throws ServiceException{
        try{
            lessonRepository.deleteLesson(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in deleteLesson");
        }
    }

}
