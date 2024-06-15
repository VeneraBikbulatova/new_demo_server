package com.example.demo.services;

import com.example.demo.entities.Attendance;
import com.example.demo.entities.Group;
import com.example.demo.entities.Lesson;
import com.example.demo.entities.Student;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.repository.IAttendanceRepository;
import com.example.demo.repository.ILessonRepository;
import com.example.demo.repository.IStudentRepository;
import com.example.demo.requests.AddAttendanceRequest;
import com.example.demo.requests.EditAttendanceRequest;
import com.example.demo.responses.GetAttendanceByIdResponse;
import com.example.demo.responses.GetLessonByIdResponse;
import com.example.demo.responses.GetStudentByIdResponse;

import java.util.List;
import java.util.stream.Collectors;

public class AttendanceService {
    private IAttendanceRepository attendanceRepository;
    private IStudentRepository studentRepository;
    private ILessonRepository lessonRepository;


    public AttendanceService(IAttendanceRepository attendanceRepository, IStudentRepository studentRepository, ILessonRepository lessonRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.lessonRepository = lessonRepository;
    }

    public long addAttendance(AddAttendanceRequest addAttendanceRequest) throws ServiceException {
        try{
            Attendance attendance = new Attendance(
                    addAttendanceRequest.getLesson_id(),
                    addAttendanceRequest.getStudent_id(),
                    0);
            attendanceRepository.addAttendance(attendance);
            return attendance.getId();
        } catch (RepositoryException r) {
            throw new ServiceException("service error in addAttendance lesson_id=" + addAttendanceRequest.getLesson_id(), r);
        }
    }

    public long editAttendance(EditAttendanceRequest editAttendanceRequest) throws ServiceException{
        try{
            Attendance attendance = new Attendance(
                    editAttendanceRequest.getLesson_id(),
                    editAttendanceRequest.getStudent_id(),
                    editAttendanceRequest.getId()
            );
            attendanceRepository.editAttendance(attendance);
            return attendance.getId();
        } catch (RepositoryException r){
            throw new ServiceException("service error in editAttendance id=" + editAttendanceRequest.getId(), r);
        }
    }

    public GetAttendanceByIdResponse getAttendanceById(long id) throws ServiceException {
        try {
            Attendance attendance = attendanceRepository.getAttendanceById(id);
            return new GetAttendanceByIdResponse(
                    attendance.getId(),
                    lessonRepository.getLessonById(attendance.getLesson_id()).getName(),
                    studentRepository.getStudentById(attendance.getStudent_id()).getFirstname(),
                    studentRepository.getStudentById(attendance.getStudent_id()).getPatronymic(),
                    studentRepository.getStudentById(attendance.getStudent_id()).getLastname()
            );
        } catch (RepositoryException r){
            throw new ServiceException("service error in getAttendanceById id=" + id, r);
        }
    }

    public List<GetAttendanceByIdResponse> getAttendancesByLessonId(long lesson_id) throws ServiceException{
        try{
            List<GetAttendanceByIdResponse> responses = attendanceRepository.getAttendances(lesson_id).stream().map(attendance -> {
                try {
                    Student student = studentRepository.getStudentById(attendance.getStudent_id());
                    Lesson lesson = lessonRepository.getLessonById(attendance.getLesson_id());
                    return new GetAttendanceByIdResponse(
                            attendance.getId(),
                            lesson.getName(),
                            student.getFirstname(),
                            student.getPatronymic(),
                            student.getLastname());
                } catch (RepositoryException e) {
                    throw new RuntimeException(e);
                }
            }).collect(Collectors.toList());
            return responses;
        } catch (RepositoryException r){
            throw new ServiceException("service error in getAllAttendances");
        }
    }

    public void deleteAttendance(long id) throws ServiceException{
        try{
            attendanceRepository.deleteAttendance(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in deleteAttendance");
        }
    }
}
