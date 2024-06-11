package com.example.demo.repository;

import com.example.demo.entities.Attendance;
import com.example.demo.exceptions.RepositoryException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;

public class AttendanceRepository implements IAttendanceRepository{
    private JdbcTemplate jdbc;

    public AttendanceRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public void addAttendance(Attendance attendance) throws RepositoryException {
        String sql = "INSERT INTO attendance(lesson_id, student_id) VALUES(?, ?)";
        try {
            jdbc.update(sql, attendance.getLesson_id(), attendance.getStudent_id());
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("ADD error on add attendance %s", sql), e
            );
        }
    }

    @Override
    public void editAttendance(Attendance attendance) throws RepositoryException {
        String sql = "UPDATE attendance SET " +
                "lesson_id = ?, " +
                "student_id = ? " +
                "WHERE id = ?";
        try{
            jdbc.update(sql, attendance.getLesson_id(), attendance.getStudent_id(), attendance.getId());
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("UPDATE error on update attendance %s, id=%d", sql, attendance.getId()), e
            );
        }
    }

    @Override
    public void deleteAttendance(long id) throws RepositoryException {
        String sql = "DELETE FROM attendance WHERE id=?";
        try{
            jdbc.update(sql, id);
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DELETE error on delete attendance by id," +
                            "\n query %s, id=%d", sql, id), e
            );
        }
    }

    @Override
    public Attendance getAttendanceById(long id) throws RepositoryException {
        String sql = "SELECT * FROM attendance WHERE id=?";
        try{
            return jdbc.queryForObject(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Attendance(
                                rs.getInt("lesson_id"),
                                rs.getInt("student_id"),
                                rs.getInt("id"));
                    },
                    id
            );
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DB error in %s, id = %d", sql, id), e
            );
        }
    }

    @Override
    public List<Attendance> getAttendances() throws RepositoryException {
        String sql = "SELECT * FROM attendance";
        try{
            return jdbc.query(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Attendance(
                                rs.getInt("lesson_id"),
                                rs.getInt("student_id"),
                                rs.getInt("id")
                        );
                    });
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DB error in %s", sql), e
            );
        }
    }
}
