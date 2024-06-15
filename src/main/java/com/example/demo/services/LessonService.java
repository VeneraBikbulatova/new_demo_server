package com.example.demo.services;

import com.example.demo.entities.Group;
import com.example.demo.entities.Lesson;
import com.example.demo.entities.Subject;
import com.example.demo.entities.Teacher;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.repository.*;
import com.example.demo.repository.ILessonRepository;
import com.example.demo.requests.AddLessonRequest;
import com.example.demo.requests.AddLessonRequest;
import com.example.demo.requests.EditLessonRequest;
import com.example.demo.responses.GetLessonByIdResponse;
import com.example.demo.responses.GetStudentByIdResponse;

import java.util.List;
import java.util.stream.Collectors;

public class LessonService {

    private ILessonRepository lessonRepository;
    private IGroupRepository groupRepository;
    private ITeacherRepository teacherRepository;
    private ISubjectRepository subjectRepository;


    public LessonService(ILessonRepository lessonRepository, IGroupRepository groupRepository, ITeacherRepository teacherRepository, ISubjectRepository subjectRepository) {
        this.lessonRepository = lessonRepository;
        this.groupRepository = groupRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
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
            Lesson lesson = lessonRepository.getLessonById(id);
            Group group = groupRepository.getGroupById(lesson.getGroup_id());
            Teacher teacher = teacherRepository.getTeacherById(lesson.getTeacher_id());
            Subject subject = subjectRepository.getSubjectById(lesson.getSubject_id());
            return new GetLessonByIdResponse(
                    lesson.getId(),
                    lesson.getName(),
                    group.getName(),
                    teacher.getFirstname(),
                    teacher.getPatronymic(),
                    teacher.getLastname(),
                    subject.getName(),
                    lesson.getLesson_date(),
                    lesson.getLesson_time()
            );
        } catch (RepositoryException r){
            throw new ServiceException("service error in getLessonById id=" + id, r);
        }
    }

    public List<GetLessonByIdResponse> getAllLessons() throws ServiceException{
        try{
            List<GetLessonByIdResponse> responses = lessonRepository.getLesson().stream().map(lesson -> {
                try {
                    Group group = groupRepository.getGroupById(lesson.getGroup_id());
                    Teacher teacher = teacherRepository.getTeacherById(lesson.getTeacher_id());
                    Subject subject = subjectRepository.getSubjectById(lesson.getSubject_id());
                    return new GetLessonByIdResponse(
                            lesson.getId(),
                            lesson.getName(),
                            group.getName(),
                            teacher.getFirstname(),
                            teacher.getPatronymic(),
                            teacher.getLastname(),
                            subject.getName(),
                            lesson.getLesson_date(),
                            lesson.getLesson_time()
                    );
                } catch (RepositoryException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            return responses;
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
