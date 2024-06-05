package com.example.demo.configurations;

import com.example.demo.repository.GroupRepository;
import com.example.demo.repository.IGroupRepository;
import com.example.demo.repository.IStudentRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

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
}
