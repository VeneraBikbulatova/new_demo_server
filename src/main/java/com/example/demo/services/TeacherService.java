package com.example.demo.services;


import com.example.demo.entities.Teacher;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.repository.ITeacherRepository;
import com.example.demo.requests.AddTeacherRequest;
import com.example.demo.requests.EditTeacherRequest;
import com.example.demo.responses.AddTeacherResponse;

import java.util.List;

public class TeacherService {
    private ITeacherRepository teacherRepository;


    public TeacherService(ITeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;

    }

    public long addTeacher(AddTeacherRequest addTeacherRequest) throws ServiceException {
        try{
            Teacher teacher = new Teacher(
                    0,
                    addTeacherRequest.getFirstname(),
                    addTeacherRequest.getPatronymic(),
                    addTeacherRequest.getLastname(),
                    addTeacherRequest.getSubject_id());
            teacherRepository.addTeacher(teacher);
            return teacher.getId();
        } catch (RepositoryException r) {
            throw new ServiceException("service error in addTeacher name=" + addTeacherRequest.getFirstname(), r);
        }
    }

    public long editTeacher(EditTeacherRequest editTeacherRequest) throws ServiceException{
        try{
            Teacher teacher = new Teacher(
                    editTeacherRequest.getId(),
                    editTeacherRequest.getFirstname(),
                    editTeacherRequest.getPatronymic(),
                    editTeacherRequest.getLastname(),
                    editTeacherRequest.getSubject_id()
            );
            teacherRepository.editTeacher(teacher);
            return teacher.getId();
        } catch (RepositoryException r){
            throw new ServiceException("service error in editTeacher id=" + editTeacherRequest.getId(), r);
        }
    }

    public Teacher getTeacherById(long id) throws ServiceException {
        try {
            return teacherRepository.getTeacherById(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in getTeacherById id=" + id, r);
        }
    }

    public List<Teacher> getTeachers() throws ServiceException {
        try {
            return teacherRepository.getTeacher();
        } catch (RepositoryException r){
            throw new ServiceException("service error in getTeachers", r);
        }
    }

    public void deleteTeacherById(long id) throws ServiceException{
        try{
            teacherRepository.deleteTeacher(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in deleteTeacher id=" + id, r);
        }
    }
}
