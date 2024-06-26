package com.example.demo.controllers;

import com.example.demo.entities.Student;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.requests.AddStudentRequest;
import com.example.demo.requests.EditStudentRequest;
import com.example.demo.responses.GetLessonByIdResponse;
import com.example.demo.responses.GetStudentByIdResponse;
import com.example.demo.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/student")
public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<?> addStudent(
            @RequestBody AddStudentRequest addStudentRequest
    ){
        try{
            return new ResponseEntity<>(studentService.addStudent(addStudentRequest), HttpStatus.OK);
        } catch (ServiceException | RepositoryException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<?> editStudent(
            @RequestBody EditStudentRequest editStudentRequest
    ){
        try{
            return new ResponseEntity<>(studentService.editStudent(editStudentRequest), HttpStatus.OK);
        } catch (ServiceException | RepositoryException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetStudentByIdResponse> getById(
            @PathVariable ("id") long id
    ){
        try {
            GetStudentByIdResponse forResponse = studentService.getStudentById(id);
            return new ResponseEntity<>(new GetStudentByIdResponse(
                    forResponse.getFirstname(),
                    forResponse.getPatronymic(),
                    forResponse.getLastname(),
                    forResponse.getGroup_name(),
                    forResponse.getId(),
                    forResponse.getStatus()
            ), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<GetStudentByIdResponse>> getAllGroups(){
        try{
            return new ResponseEntity<List<GetStudentByIdResponse>>(studentService.getStudents(), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RepositoryException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(
            @PathVariable ("id") long id
    ){
        try{
            studentService.deleteStudentById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(ServiceException | RepositoryException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
