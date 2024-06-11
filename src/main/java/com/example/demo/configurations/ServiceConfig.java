package com.example.demo.configurations;


import com.example.demo.repository.*;
import com.example.demo.services.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public GroupService groupService(IGroupRepository groupRepository){
        return new GroupService(groupRepository);
    }

    @Bean
    public StudentService studentService(IStudentRepository studentRepository){
        return new StudentService(studentRepository);
    }

    @Bean
    public TeacherService teacherService(ITeacherRepository teacherRepository){
        return new TeacherService(teacherRepository);
    }

    @Bean
    public LessonService lessonService(ILessonRepository lessonRepository){
        return new LessonService(lessonRepository);
    }

    @Bean
    public AttendanceService attendanceService(IAttendanceRepository attendanceRepository){
        return new AttendanceService(attendanceRepository);
    }

    @Bean
    public SubjectService subjectService(ISubjectRepository subjectRepository){
        return new SubjectService(subjectRepository);
    }
}
