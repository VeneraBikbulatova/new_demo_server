package com.example.demo.repository;

import com.example.demo.entities.Subject;
import com.example.demo.exceptions.RepositoryException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class SubjectRepository implements ISubjectRepository{
    private JdbcTemplate jdbc;

    public SubjectRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long addSubject(Subject subject) throws RepositoryException {
        String sql = "INSERT INTO subject(name) VALUES(?)";
        try{
            PreparedStatementCreator psc = con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, subject.getName());
                return ps;
            };
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(psc, keyHolder);
            return keyHolder.getKey().longValue();
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("ADD error on add subject %s, id=%d", sql, subject.getId()), e
            );
        }
    }

    @Override
    public void editSubject(Subject subject) throws RepositoryException {
        String sql = "UPDATE subject SET name = ? WHERE id = ?";
        try{
            jdbc.update(sql, subject.getName(), subject.getId());
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("UPDATE error on update subject %s, id=%d", sql, subject.getId()), e
            );
        }
    }

    @Override
    public void deleteSubject(long id) throws RepositoryException {
        String sql = "DELETE FROM subject WHERE id=?";
        try{
            jdbc.update(sql, id);
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DELETE error on delete subject by id," +
                            "\n query %s, id=%d", sql, id), e
            );
        }
    }

    @Override
    public Subject getSubjectById(long id) throws RepositoryException {
        String sql = "SELECT * FROM subject WHERE id=?";
        try{
            return jdbc.queryForObject(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Subject(rs.getInt("id"), rs.getString("name"));
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
    public List<Subject> getSubjects() throws RepositoryException {
        String sql = "SELECT * FROM subject";
        try{
            return jdbc.query(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Subject(
                                rs.getInt("id"),
                                rs.getString("name"));
                    });
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DB error in %s", sql), e
            );
        }
    }
}
