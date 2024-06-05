package com.example.demo.configurations;


import com.example.demo.repository.IGroupRepository;
import com.example.demo.repository.IStudentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.services.GroupService;
import com.example.demo.services.StudentService;
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
}
