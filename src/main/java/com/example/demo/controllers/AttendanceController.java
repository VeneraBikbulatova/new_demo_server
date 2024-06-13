package com.example.demo.controllers;

import com.example.demo.entities.Attendance;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.requests.AddAttendanceRequest;
import com.example.demo.requests.EditAttendanceRequest;
import com.example.demo.responses.GetAttendanceByIdResponse;
import com.example.demo.services.AttendanceService;
import com.example.demo.services.LessonService;
import com.example.demo.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private AttendanceService attendanceService;
    private LessonService lessonService;
    private StudentService studentService;

    public AttendanceController(AttendanceService attendanceService, LessonService lessonService, StudentService studentService) {
        this.attendanceService = attendanceService;
        this.lessonService = lessonService;
        this.studentService = studentService;
    }



    @PostMapping()
    public ResponseEntity<?> addAttendance(
            @RequestBody AddAttendanceRequest addAttendanceRequest
    ){
        try{
            return new ResponseEntity<>(attendanceService.addAttendance(addAttendanceRequest), HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ResponseEntity<?> editAttendance(
            @RequestBody EditAttendanceRequest editAttendanceRequest
    ){
        try{
            return new ResponseEntity<>(attendanceService.editAttendance(editAttendanceRequest), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetAttendanceByIdResponse> getById(
            @PathVariable("id") long id
    ){
        try {
            Attendance forResponse = attendanceService.getAttendanceById(id);
            return new ResponseEntity<>(new GetAttendanceByIdResponse(
                    id,
                    lessonService.getLessonById(forResponse.getLesson_id()).getName(),
                    studentService.getStudentById(forResponse.getStudent_id()).getFirstname(),
                    studentService.getStudentById(forResponse.getStudent_id()).getPatronymic(),
                    studentService.getStudentById(forResponse.getStudent_id()).getLastname()), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Attendance>> getAttendancesByLessonId(){
        try{
            return new ResponseEntity<List<Attendance>>(attendanceService.getAttendancesByLessonId(), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendance(
            @PathVariable ("id") long id
    ){
        try{
            attendanceService.deleteAttendance(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
