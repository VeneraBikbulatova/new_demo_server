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
    public StudentService studentService(IStudentRepository studentRepository, IGroupRepository groupRepository){
        return new StudentService(studentRepository, groupRepository);
    }

    @Bean
    public TeacherService teacherService(ITeacherRepository teacherRepository){
        return new TeacherService(teacherRepository);
    }

    @Bean
    public LessonService lessonService(ILessonRepository lessonRepository, IGroupRepository groupRepository, ITeacherRepository teacherRepository, ISubjectRepository subjectRepository){
        return new LessonService(lessonRepository, groupRepository, teacherRepository, subjectRepository);
    }

    @Bean
    public AttendanceService attendanceService(IAttendanceRepository attendanceRepository, IStudentRepository studentRepository, ILessonRepository lessonRepository){
        return new AttendanceService(attendanceRepository, studentRepository, lessonRepository);
    }

    @Bean
    public SubjectService subjectService(ISubjectRepository subjectRepository){
        return new SubjectService(subjectRepository);
    }
}
