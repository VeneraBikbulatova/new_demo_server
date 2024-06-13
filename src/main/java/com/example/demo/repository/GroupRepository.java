package com.example.demo.repository;

import com.example.demo.entities.Group;
import com.example.demo.exceptions.RepositoryException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class GroupRepository implements IGroupRepository{
    private JdbcTemplate jdbc;


    public GroupRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long addGroup(Group group) throws RepositoryException {
        String sql = "INSERT INTO student_group(name) VALUES(?);";
        try{
            PreparedStatementCreator psc = con -> {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, group.getName());
                return ps;
            };
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(psc, keyHolder);
            return keyHolder.getKey().longValue();
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("ADD error on add group %s, id=%d", sql, group.getGroupId()), e
            );
        }
    }

    @Override
    public void editGroup(Group group) throws RepositoryException {
        String sql = "UPDATE student_group SET id = ?, name = ? WHERE id = ?)";
        try{
            jdbc.update(sql, group.getGroupId(), group.getName(), group.getGroupId());
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("UPDATE error on update group %s, id=%d", sql, group.getGroupId()), e
            );
        }
    }

    @Override
    public void deleteGroup(long id) throws RepositoryException {
        String sql = "DELETE FROM student_group WHERE id=?";
        try{
            jdbc.update(sql, id);
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DELETE error on delete group by id," +
                            "\n query %s, id=%d", sql, id), e
            );
        }
    }

    @Override
    public Group getGroupById(long id) throws RepositoryException {
        String sql = "SELECT * FROM student_group WHERE id=?";
        try{
            return jdbc.queryForObject(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Group(rs.getInt("id"), rs.getString("name"));
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
    public List<Group> getGroups() throws RepositoryException {
        String sql = "SELECT * FROM student_group";
        try{
            return jdbc.query(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Group(
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
