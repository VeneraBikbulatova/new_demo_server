package com.example.demo.repository;

import com.example.demo.entities.Attendance;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.responses.GetAttendanceByIdResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class AttendanceRepository implements IAttendanceRepository{
    private JdbcTemplate jdbc;
    private StudentRepository studentRepository;
    private LessonRepository lessonRepository;

    public AttendanceRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long addAttendance(Attendance attendance) throws RepositoryException {
        String sql = "INSERT INTO attendance(lesson_id, student_id) VALUES(?, ?)";
        try {
            PreparedStatementCreator psc = con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, attendance.getLesson_id());
                ps.setLong(2, attendance.getStudent_id());
                return ps;
            };
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(psc, keyHolder);
            return keyHolder.getKey().longValue();
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
    public GetAttendanceByIdResponse getAttendanceById(long id) throws RepositoryException {
        String sql = "SELECT * FROM attendance WHERE id=?";
        try{
            return jdbc.queryForObject(sql,
                    (ResultSet rs, int rownumber)->{
                        try {
                            return new GetAttendanceByIdResponse(
                                    rs.getInt("id"),
                                    lessonRepository.getLessonById(rs.getInt("lesson_id")).getName(),
                                    studentRepository.getStudentById(rs.getInt("student_id")).getFirstname(),
                                    studentRepository.getStudentById(rs.getInt("student_id")).getPatronymic(),
                                    studentRepository.getStudentById(rs.getInt("student_id")).getLastname());
                        } catch (RepositoryException e) {
                            throw new RuntimeException(e);
                        }
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
    public List<GetAttendanceByIdResponse> getAttendances(long lesson_id) throws RepositoryException {
        String sql = "SELECT * FROM attendance WHERE lesson_id = ?";
        try{
            return jdbc.query(sql,
                    (ResultSet rs, int rownumber)->{
                        try {
                            return new GetAttendanceByIdResponse(
                                    rs.getInt("id"),
                                    lessonRepository.getLessonById(rs.getInt("lesson_id")).getName(),
                                    studentRepository.getStudentById(rs.getInt("student_id")).getFirstname(),
                                    studentRepository.getStudentById(rs.getInt("student_id")).getPatronymic(),
                                    studentRepository.getStudentById(rs.getInt("student_id")).getLastname()
                            );
                        } catch (RepositoryException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    lesson_id);
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DB error in %s", sql), e
            );
        }
    }
}
