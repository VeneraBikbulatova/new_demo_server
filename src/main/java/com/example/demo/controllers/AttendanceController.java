package com.example.demo.controllers;

import com.example.demo.entities.Attendance;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.requests.AddAttendanceRequest;
import com.example.demo.requests.EditAttendanceRequest;
import com.example.demo.responses.GetAttendanceByIdResponse;
import com.example.demo.services.AttendanceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    private AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
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
                    forResponse.getLesson_id(),
                    forResponse.getStudent_id(),
                    forResponse.getId()), HttpStatus.OK);
        } catch (ServiceException s){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Attendance>> getAllAttendances(){
        try{
            return new ResponseEntity<List<Attendance>>(attendanceService.getAllAttendances(), HttpStatus.OK);
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
