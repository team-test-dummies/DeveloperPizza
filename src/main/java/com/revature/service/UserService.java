package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public List<User> getAllUsers() throws SQLException {
        return userDao.getAllUsers();
    }
}
