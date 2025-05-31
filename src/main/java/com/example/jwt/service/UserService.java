package com.example.jwt.service;

import com.example.jwt.dao.UserDao;
import com.example.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User registerUser(User user) {

        return userDao.save(user);

    }
}
