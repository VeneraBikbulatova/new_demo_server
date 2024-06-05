package com.example.demo.services;

import com.example.demo.entities.Student;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.repository.IStudentRepository;
import com.example.demo.requests.AddStudentRequest;
import com.example.demo.requests.EditStudentRequest;

import java.util.List;

public class StudentService {
    private IStudentRepository studentRepository;


    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;

    }

    public long addStudent(AddStudentRequest addStudentRequest) throws ServiceException {
        try{
            Student student = new Student(
                    addStudentRequest.getFirstname(),
                    addStudentRequest.getPatronymic(),
                    addStudentRequest.getLastname(),
                    0,
                    addStudentRequest.getGroupId(),
                    addStudentRequest.getStatus());
            studentRepository.addStudent(student);
            return student.getId();
        } catch (RepositoryException r) {
            throw new ServiceException("service error in addStudent name=" + addStudentRequest.getFirstname(), r);
        }
    }

    public long editStudent(EditStudentRequest editStudentRequest) throws ServiceException{
        try{
            Student student = new Student(
                    editStudentRequest.getFirstname(),
                    editStudentRequest.getPatronymic(),
                    editStudentRequest.getLastname(),
                    editStudentRequest.getGroupId(),
                    editStudentRequest.getId(),
                    editStudentRequest.getStatus()
            );
            studentRepository.editStudent(student);
            return student.getId();
        } catch (RepositoryException r){
            throw new ServiceException("service error in editStudent id=" + editStudentRequest.getId(), r);
        }
    }

    public Student getStudentById(long id) throws ServiceException {
        try {
            return studentRepository.getStudentById(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in getStudentById id=" + id, r);
        }
    }

    public List<Student> getStudents() throws ServiceException {
        try {
            return studentRepository.getStudents();
        } catch (RepositoryException r){
            throw new ServiceException("service error in getStudents", r);
        }
    }

    public void deleteStudentById(long id) throws ServiceException{
        try{
            studentRepository.deleteStudent(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in deleteStudent id=" + id, r);
        }
    }

}
