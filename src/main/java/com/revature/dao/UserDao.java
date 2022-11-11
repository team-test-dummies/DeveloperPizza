package com.revature.dao;

import com.revature.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public User findUserByUsernameAndPassword(String username, String password) throws SQLException, IOException {
        try (Connection connection = ConnectionFactory.createConnection()) {
            String sql = "select * from users where username = ? and password = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getString("accounttype"));
                return user;
            } else {
                return null;
            }
        }
    }
}
