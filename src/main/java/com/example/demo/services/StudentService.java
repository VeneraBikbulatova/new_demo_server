package com.example.demo.services;

import com.example.demo.entities.Group;
import com.example.demo.entities.Student;
import com.example.demo.exceptions.NotFoundService;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.repository.IGroupRepository;
import com.example.demo.repository.IStudentRepository;
import com.example.demo.requests.AddStudentRequest;
import com.example.demo.requests.EditStudentRequest;
import com.example.demo.responses.GetLessonByIdResponse;
import com.example.demo.responses.GetStudentByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.stream.Collectors;


import java.util.List;
import java.util.Optional;

public class StudentService {
    @Autowired
    private IStudentRepository studentRepository;
    @Autowired
    private IGroupRepository groupRepository;


    public StudentService(IStudentRepository studentRepository, IGroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    public long addStudent(AddStudentRequest addStudentRequest) throws ServiceException, RepositoryException, NotFoundService {
        Optional.of(groupRepository.getGroupById(addStudentRequest.getGroupId())).orElseThrow(() -> new NotFoundService("invalid group id"));
        try{
            long id = studentRepository.addStudent(addStudentRequest);
            return id;
        } catch (RepositoryException r) {
            throw new ServiceException("service error in addStudent name=" + addStudentRequest.getFirstname(), r);
        }
    }

    public long editStudent(EditStudentRequest editStudentRequest) throws ServiceException, RepositoryException {
        Optional.of(groupRepository.getGroupById(editStudentRequest.getGroupId())).orElseThrow(() -> new NotFoundService("invalid group id"));
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

    public GetStudentByIdResponse getStudentById(long id) throws ServiceException {
        try {
            Student student = studentRepository.getStudentById(id);
            return new GetStudentByIdResponse(
                    student.getLastname(),
                    student.getFirstname(),
                    student.getPatronymic(),
                    groupRepository.getGroupById(student.getGroup_id()).getName(),
                    student.getId(),
                    student.getStatus()
            );
        } catch (RepositoryException r){
            throw new ServiceException("service error in getStudentById id=" + id, r);
        }
    }

    public List<GetStudentByIdResponse> getStudents() throws ServiceException, RepositoryException {
        List<GetStudentByIdResponse> responses = studentRepository.getStudents().stream().map(student -> {
            try {
                Group group = groupRepository.getGroupById(student.getGroup_id());
                return new GetStudentByIdResponse(
                        student.getLastname(),
                        student.getFirstname(),
                        student.getPatronymic(),
                        group.getName(),
                        student.getId(),
                        student.getStatus());
            } catch (RepositoryException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        return responses;
    }

    public void deleteStudentById(long id) throws ServiceException, RepositoryException, NotFoundService {
        Optional.of(studentRepository.getStudentById(id)).orElseThrow(() -> new NotFoundService("invalid group id"));
        try{
            studentRepository.deleteStudent(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in deleteStudent id=" + id, r);
        }
    }

//    public GetStudentByIdResponse convertStudentToResponse(Student student, List<Group> groups) {
//        String groupName = groups.stream()
//                .filter(group -> group.getGroupId() == student.getGroup_id())
//                .findFirst()
//                .map(Group::getName)
//                .orElse(null);
//
//        return new GetStudentByIdResponse(
//                student.getLastname(),
//                student.getFirstname(),
//                student.getPatronymic(),
//                groupName,
//                student.getId(),
//                student.getStatus()
//        );
//    }


}
