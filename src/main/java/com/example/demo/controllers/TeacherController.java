package com.example.demo.controllers;

import com.example.demo.entities.Teacher;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.requests.AddTeacherRequest;
import com.example.demo.requests.EditTeacherRequest;
import com.example.demo.responses.GetTeacherByIdResponse;
import com.example.demo.services.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping()
    public ResponseEntity<?> addTeacher(
            @RequestBody AddTeacherRequest addTeacherRequest
    ){
        try{
            return new ResponseEntity<>(teacherService.addTeacher(addTeacherRequest), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<?> editTeacher(
            @RequestBody EditTeacherRequest editTeacherRequest
    ){
        try{
            return new ResponseEntity<>(teacherService.editTeacher(editTeacherRequest), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetTeacherByIdResponse> getById(
            @PathVariable("id") long id
    ){
        try {
            Teacher forResponse = teacherService.getTeacherById(id);
            return new ResponseEntity<>(new GetTeacherByIdResponse(
                    forResponse.getId(),
                    forResponse.getFirstname(),
                    forResponse.getPatronymic(),
                    forResponse.getLastname(),
                    forResponse.getSubject_id()
            ), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Teacher>> getAllTeachers(){
        try{
            return new ResponseEntity<List<Teacher>>(teacherService.getTeachers(), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(
            @PathVariable ("id") long id
    ){
        try{
            teacherService.deleteTeacherById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
