package com.example.demo.repository;

import com.example.demo.entities.Group;
import com.example.demo.entities.Lesson;
import com.example.demo.exceptions.RepositoryException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.util.List;

public class LessonRepository implements ILessonRepository{
    private JdbcTemplate jdbc;

    public LessonRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public long addLesson(Lesson lesson) throws RepositoryException {
        String sql = "INSERT INTO lesson(name, group_id, teacher_id, subject_id, lesson_date, lesson_time) VALUES(?, ?, ?, ?, ?, ?)";
        try{
            jdbc.update(
                    sql,
                    lesson.getName(),
                    lesson.getGroup_id(),
                    lesson.getTeacher_id(),
                    lesson.getSubject_id(),
                    lesson.getLesson_date(),
                    lesson.getLesson_time());
            return lesson.getId();
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("ADD error on add lesson %s, id=%d", sql, lesson.getId()), e
            );
        }
    }

    @Override
    public void editLesson(Lesson lesson) throws RepositoryException {
        String sql = "UPDATE lesson SET " +
                "name = ?," +
                "group_id = ?," +
                "teacher_id = ?," +
                "subject_id = ?," +
                "lesson_date = ?," +
                "lesson_time = ? " +
                "WHERE id = ?";
        try{
            jdbc.update(
                    sql,
                    lesson.getName(),
                    lesson.getGroup_id(),
                    lesson.getTeacher_id(),
                    lesson.getSubject_id(),
                    lesson.getLesson_date(),
                    lesson.getLesson_time(),
                    lesson.getId()
                    );
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("UPDATE error on update lesson %s, id=%d", sql, lesson.getId()), e
            );
        }
    }

    @Override
    public void deleteLesson(long id) throws RepositoryException {
        String sql = "DELETE FROM lesson WHERE id=?";
        try{
            jdbc.update(sql, id);
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DELETE error on delete lesson by id," +
                            "\n query %s, id=%d", sql, id), e
            );
        }
    }

    @Override
    public Lesson getLessonById(long id) throws RepositoryException {
        String sql = "SELECT * FROM lesson WHERE id=?";
        try{
            return jdbc.queryForObject(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Lesson(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("group_id"),
                                rs.getInt("teacher_id"),
                                rs.getInt("subject_id"),
                                rs.getDate("lesson_date"),
                                rs.getInt("lesson_time")
                        );
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
    public List<Lesson> getLesson() throws RepositoryException {
        String sql = "SELECT * FROM lesson";
        try{
            return jdbc.query(sql,
                    (ResultSet rs, int rownumber)->{
                        return new Lesson(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("group_id"),
                                rs.getInt("teacher_id"),
                                rs.getInt("subject_id"),
                                rs.getDate("lesson_date"),
                                rs.getInt("lesson_time")
                        );
                    });
        } catch (Exception e){
            throw new RepositoryException(
                    String.format("DB error in %s", sql), e
            );
        }
    }
}
