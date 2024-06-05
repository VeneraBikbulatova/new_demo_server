package com.example.demo.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.beans.ConstructorProperties;

@Configuration

public class DatabaseConfig {
    @ConfigurationProperties (prefix = "spring.datasource")
    @Bean
    public DataSource dataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
