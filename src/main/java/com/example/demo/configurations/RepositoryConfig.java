package com.example.demo.configurations;

import com.example.demo.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import java.beans.BeanProperty;

@Configuration
public class RepositoryConfig {
    @Bean
    public IGroupRepository groupRepository(JdbcTemplate jdbc){
        return new GroupRepository(jdbc);
    }

    @Bean
    public IStudentRepository studentRepository(JdbcTemplate jdbc){
        return new StudentRepository(jdbc);
    }

    @Bean
    public ITeacherRepository teacherRepository(JdbcTemplate jdbc){
        return new TeacherRepository(jdbc);
    }

    @Bean
    public ILessonRepository lessonRepository(JdbcTemplate jdbc){
        return new LessonRepository(jdbc);
    }

    @Bean
    public IAttendanceRepository attendanceRepository(JdbcTemplate jdbc){
        return new AttendanceRepository(jdbc);
    }

    @Bean
    public ISubjectRepository subjectRepository(JdbcTemplate jdbc){
        return new SubjectRepository(jdbc);
    }
}
