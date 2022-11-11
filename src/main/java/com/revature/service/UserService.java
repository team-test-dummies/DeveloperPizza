package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.exception.LoginException;
import com.revature.model.Developer;
import com.revature.model.User;

import java.io.IOException;
import java.sql.SQLException;

public class UserService {
    private UserDao userDao = new UserDao();
    public User login(String username, String password) throws SQLException, IOException {
        User user = userDao.findUserByUsernameAndPassword(username, password);
        if (user == null) {
            throw new LoginException("Invalid Login");
        } else {
            return user;
        }
    }
}
