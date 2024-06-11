package com.example.demo.controllers;

import com.example.demo.entities.Lesson;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.requests.AddLessonRequest;
import com.example.demo.requests.EditLessonRequest;
import com.example.demo.responses.GetLessonByIdResponse;
import com.example.demo.services.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lesson")

public class LessonController {
    private LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetLessonByIdResponse> getById(
            @PathVariable("id") long id
    ){
        try {
            Lesson forResponse = lessonService.getLessonById(id);
            return new ResponseEntity<>(new GetLessonByIdResponse(
                    forResponse.getId(),
                    forResponse.getName(),
                    forResponse.getGroup_id(),
                    forResponse.getTeacher_id(),
                    forResponse.getSubject_id(),
                    forResponse.getLesson_date(),
                    forResponse.getLesson_time())
                    , HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Lesson>> getAllLessons(){
        try{
            return new ResponseEntity<List<Lesson>>(lessonService.getAllLessons(), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping()
    public ResponseEntity<?> addLesson(
            @RequestBody AddLessonRequest addLessonRequest
            ){
        try{
            return new ResponseEntity<>(lessonService.addLesson(addLessonRequest), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<?> editLesson(
            @RequestBody EditLessonRequest editGroupRequest
            ){
        try{
            return new ResponseEntity<>(lessonService.editLesson(editGroupRequest), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLesson(
            @PathVariable ("id") long id
    ){
        try{
            lessonService.deleteLesson(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
