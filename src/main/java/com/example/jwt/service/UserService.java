package com.example.jwt.service;

import com.example.jwt.dao.RoleDao;
import com.example.jwt.dao.UserDao;
import com.example.jwt.entity.Role;
import com.example.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder encodedPasswor;

    public User registerUser(User user) {

        return userDao.save(user);
    }

    public void initRoleAndUser() {

        //Create Admin Role
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        //Create User role
        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        // Create Admin User
        User adminUser = new User();
        adminUser.setFirstName("admin");
        adminUser.setLastName("admin");
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        //Create Regular user
        User user = new User();
        user.setFirstName("Rajesh");
        user.setLastName("Sharma");
        user.setUserName("raj123");
        user.setUserPassword(getEncodedPassword("raj@pass"));

        Set<Role> userRoles = new HashSet<>();
        user.setRole(userRoles);
        userRoles.add(userRole);
        userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return encodedPasswor.encode(password);
    }

}
