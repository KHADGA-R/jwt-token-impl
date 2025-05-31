package com.example.jwt.service;

import com.example.jwt.dao.RoleDao;
import com.example.jwt.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

        @Autowired
        private RoleDao roleDao;

        // Implementation to create a new role
        // You can add validation, business logic, and other necessary operations here
        // For example, checking if the role already exists, validating the role name, etc.
        // After creating the role, you can return the created role object
    public Role createRole(Role role) {

        return roleDao.save(role);
    }
}
