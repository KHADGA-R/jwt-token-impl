package com.example.jwt.controller;

import com.example.jwt.entity.Role;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    // RoleController.java
    // This class will handle the role-related operations
    // such as creating, updating, and deleting roles
    // You can add methods here to handle the corresponding HTTP requests
    // and interact with the RoleService to perform the necessary operations
    // on the Role entity.

    @PostMapping({"/createRole"})
    public Role createRole(Role role) {

    }
}
