package com.example.demo.controllers;


import com.example.demo.entities.Subject;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.requests.AddSubjectRequest;
import com.example.demo.requests.EditSubjectRequest;
import com.example.demo.responses.GetSubjectByIdResponse;
import com.example.demo.services.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping()
    public ResponseEntity<?> addSubject(
            @RequestBody AddSubjectRequest addSubjectRequest
    ){
        try{
            return new ResponseEntity<>(subjectService.addSubject(addSubjectRequest), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<?> editSubject(
            @RequestBody EditSubjectRequest editSubjectRequest
    ){
        try{
            return new ResponseEntity<>(subjectService.editSubject(editSubjectRequest), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetSubjectByIdResponse> getById(
            @PathVariable("id") long id
    ){
        try {
            Subject forResponse = subjectService.getSubjectById(id);
            return new ResponseEntity<>(new GetSubjectByIdResponse(
                    forResponse.getId(),
                    forResponse.getName()
            ), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Subject>> getAllSubjects(){
        try{
            return new ResponseEntity<List<Subject>>(subjectService.getAllSubjects(), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(
            @PathVariable ("id") long id
    ){
        try{
            subjectService.deleteSubject(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
