package com.example.jwt.dao;

import com.example.jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, String>{
}
