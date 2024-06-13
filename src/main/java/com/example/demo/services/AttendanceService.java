package com.example.demo.services;

import com.example.demo.entities.Attendance;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.exceptions.ServiceException;
import com.example.demo.repository.IAttendanceRepository;
import com.example.demo.requests.AddAttendanceRequest;
import com.example.demo.requests.EditAttendanceRequest;
import com.example.demo.responses.GetAttendanceByIdResponse;

import java.util.List;

public class AttendanceService {
    private IAttendanceRepository attendanceRepository;


    public AttendanceService(IAttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
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
            return attendanceRepository.getAttendanceById(id);
        } catch (RepositoryException r){
            throw new ServiceException("service error in getAttendanceById id=" + id, r);
        }
    }

    public List<GetAttendanceByIdResponse> getAttendancesByLessonId(long lesson_id) throws ServiceException{
        try{
            return attendanceRepository.getAttendances(lesson_id);
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
