package com.example.demo.repository;

import com.example.demo.entities.Student;
import com.example.demo.exceptions.RepositoryException;
import com.example.demo.requests.AddStudentRequest;
import com.example.demo.responses.GetLessonByIdResponse;
import com.example.demo.responses.GetStudentByIdResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.*;
import java.util.List;

public class StudentRepository implements IStudentRepository{

    private JdbcTemplate jdbc;
    private GroupRepository groupRepository;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long addStudent(AddStudentRequest addStudentRequest) throws RepositoryException {
        String sql = "INSERT INTO student(firstname, patronymic, lastname, group_id, status) VALUES(?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        try{
            PreparedStatementCreator psc = con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, addStudentRequest.getFirstname());
                ps.setString(2, addStudentRequest.getPatronymic());
                ps.setString(3, addStudentRequest.getLastname());
                ps.setLong(4, addStudentRequest.getGroup());
                ps.setString(5, addStudentRequest.getStatus());
                return ps;
            };
            jdbc.update(psc, keyHolder);
            return keyHolder.getKey().longValue();
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("ADD error on add student %s, id=%d", sql, keyHolder.getKey().longValue()), e
            );
        }
    }

    @Override
    public void editStudent(Student student) throws RepositoryException {
        String sql = "UPDATE student SET " +
                "firstname = ?, " +
                "patronymic = ?, " +
                "lastname = ?, " +
                "group_id = ?, " +
                "id = ?, " +
                "status = ? " +
                "WHERE id = ?";
        try{
            jdbc.update(
                    sql,
                    student.getFirstname(),
                    student.getPatronymic(),
                    student.getLastname(),
                    student.getGroup(),
                    student.getId(),
                    student.getStatus(),
                    student.getId());
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("EDIT error on edit student %s, id=%d", sql, student.getId()), e
            );
        }
    }

    @Override
    public void deleteStudent(long id) throws RepositoryException {
        String sql = "DELETE FROM student WHERE id=?";
        try{
            jdbc.update(sql, id);
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DELETE error on delete student by id," +
                            "\n query %s, id=%d", sql, id), e
            );
        }
    }

    @Override
    public GetStudentByIdResponse getStudentById(long id) throws RepositoryException {
        String sql = "SELECT * FROM student WHERE id=?";
        try{
            return jdbc.queryForObject(sql,
                    (ResultSet rs, int rownumber)->{
                        try {
                            return new GetStudentByIdResponse(
                                    rs.getString("firstname"),
                                    rs.getString("patronymic"),
                                    rs.getString("lastname"),
                                    groupRepository.getGroupById(rs.getInt("group_id")).getName(),
                                    rs.getInt("id"),
                                    rs.getString("status"));
                        } catch (RepositoryException e) {
                            throw new RuntimeException(e);
                        }
                    },
                    id
            );
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("GET error in getStudentById %s, id = %d", sql, id), e
            );
        }
    }

    @Override
    public List<GetStudentByIdResponse> getStudents() throws RepositoryException {
        String sql = "SELECT * FROM student";
        try{
            return jdbc.query(sql,
                    (ResultSet rs, int rownumber)->{
                        try {
                            return new GetStudentByIdResponse(
                                    rs.getString("firstname"),
                                    rs.getString("patronymic"),
                                    rs.getString("lastname"),
                                    groupRepository.getGroupById(rs.getInt("group_id")).getName(),
                                    rs.getInt("id"),
                                    rs.getString("status"));
                        } catch (RepositoryException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DB error in %s", sql), e
            );
        }
    }
}
