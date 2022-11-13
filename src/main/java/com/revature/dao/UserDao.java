package com.revature.dao;


import com.revature.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    public List<User> getAllUsers() throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users");
            ResultSet rs = pstmt.executeQuery();
            List<User> allUsers = new ArrayList<>();

            while (rs.next()) {
                int id              = rs.getInt(1);
                String acountType   = rs.getString("accountType");
                String accountName  = rs.getString("accountName");
                String username     = rs.getString("username");
                String password     = rs.getString("password");
                String phoneNumber  = rs.getString("phoneNumber");
                String email        = rs.getString("email");
                String location     = rs.getString("location");

                User user = new User(id, acountType, accountName, username, password, phoneNumber, email, location);

                allUsers.add(user);
            }
            return allUsers;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
