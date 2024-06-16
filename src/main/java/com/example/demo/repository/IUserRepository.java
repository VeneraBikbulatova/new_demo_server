package com.example.demo.repository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    @Transactional
    @Query("SELECT us FROM User us WHERE us.user_name = :user_name")
    Optional<User> findByName(@Param(value = "user_name") String username);
}
