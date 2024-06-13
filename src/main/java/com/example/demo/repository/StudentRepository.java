package com.example.demo.repository;

import com.example.demo.entities.Student;
import com.example.demo.exceptions.RepositoryException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;

public class StudentRepository implements IStudentRepository{

    private JdbcTemplate jdbc;

    public StudentRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long addStudent(Student student) throws RepositoryException {
        String sql = "INSERT INTO student(firstname, patronymic, lastname, group_id, status) VALUES(?, ?, ?, ?, ?)";
        try{
            jdbc.update(
                    sql,
                    student.getFirstname(),
                    student.getPatronymic(),
                    student.getLastname(),
                    student.getGroup(),
                    student.getStatus());
            return student.getId();
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("ADD error on add student %s, id=%d", sql, student.getId()), e
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
    public Student getStudentById(long id) throws RepositoryException {
        String sql = "SELECT * FROM student WHERE id=?";
        try{
            return jdbc.queryForObject(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Student(
                                rs.getString("firstname"),
                                rs.getString("patronymic"),
                                rs.getString("lastname"),
                                rs.getInt("group_id"),
                                rs.getInt("id"),
                                rs.getString("status"));
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
    public List<Student> getStudents() throws RepositoryException {
        String sql = "SELECT * FROM student";
        try{
            return jdbc.query(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Student(
                                rs.getString("firstname"),
                                rs.getString("patronymic"),
                                rs.getString("lastname"),
                                rs.getInt("group_id"),
                                rs.getInt("id"),
                                rs.getString("status"));
                    });
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DB error in %s", sql), e
            );
        }
    }
}
