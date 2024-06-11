package com.example.demo.repository;

import com.example.demo.entities.Student;
import com.example.demo.entities.Teacher;
import com.example.demo.exceptions.RepositoryException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;

public class TeacherRepository implements ITeacherRepository{
    private JdbcTemplate jdbc;

    public TeacherRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long addTeacher(Teacher teacher) throws RepositoryException {
        String sql = "INSERT INTO teacher(firstname, patronymic, lastname, subject_id) VALUES(?, ?, ?, ?)";
        try{
            jdbc.update(
                    sql,
                    teacher.getFirstname(),
                    teacher.getPatronymic(),
                    teacher.getLastname(),
                    teacher.getSubject_id());
            return teacher.getId();
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("ADD error on add teacher %s, id=%d", sql, teacher.getId()), e
            );
        }
    }

    @Override
    public void editTeacher(Teacher teacher) throws RepositoryException {
        String sql = "UPDATE teacher SET " +
                "firstname = ?, " +
                "patronymic = ?, " +
                "lastname = ?, " +
                "subject_id = ? " +
                "WHERE id = ?";
        try{
            jdbc.update(
                    sql,
                    teacher.getFirstname(),
                    teacher.getPatronymic(),
                    teacher.getLastname(),
                    teacher.getSubject_id(),
                    teacher.getId());
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("EDIT error on edit teacher %s, id=%d", sql, teacher.getId()), e
            );
        }
    }

    @Override
    public void deleteTeacher(long id) throws RepositoryException {
        String sql = "DELETE FROM teacher WHERE id=?";
        try{
            jdbc.update(sql, id);
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DELETE error on delete teacher by id," +
                            "\n query %s, id=%d", sql, id), e
            );
        }
    }

    @Override
    public Teacher getTeacherById(long id) throws RepositoryException {
        String sql = "SELECT * FROM teacher WHERE id=?";
        try{
            return jdbc.queryForObject(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Teacher(
                                rs.getInt("id"),
                                rs.getString("firstname"),
                                rs.getString("patronymic"),
                                rs.getString("lastname"),
                                rs.getInt("subject_id"));

                    },
                    id
            );
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("GET error in getTeacherById %s, id = %d", sql, id), e
            );
        }
    }

    @Override
    public List<Teacher> getTeacher() throws RepositoryException {
        String sql = "SELECT * FROM teacher";
        try{
            return jdbc.query(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Teacher(
                                rs.getInt("id"),
                                rs.getString("firstname"),
                                rs.getString("patronymic"),
                                rs.getString("lastname"),
                                rs.getInt("subject_id"));
                    });
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DB error in %s", sql), e
            );
        }
    }
}
