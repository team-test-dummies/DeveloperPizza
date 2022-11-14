package com.revature.dao;


import com.revature.dto.EditProfile;
import com.revature.dto.RegisterInfo;
import com.revature.model.Employer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployerDao extends Dao {
    public static List<Employer> getAllEmployers() throws SQLException, IOException {
        try(Connection connection = createConnection()) {
            String sql = "SELECT * FROM users";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            List<Employer> allEmployers = new ArrayList<>();

            // Employer Record --> Employer Object
            while (rs.next()) {
                int id = rs.getInt("id");
                String accountType = rs.getString("accounttype");
                String accountName = rs.getString("accountname");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phonenumber");
                String email = rs.getString("email");
                String location = rs.getString("location");

                Employer e = new Employer(id, accountType, accountName, username, password, phoneNumber, email, location);

                allEmployers.add(e); // Store Employer in ArrayList
            }
            return allEmployers;
        }
    }

    public static int registerEmployer(RegisterInfo account) throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO users (accounttype, accountname, username, password, phonenumber, email, location) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            pstmt.setString(1, account.getAccountType());
            pstmt.setString(2, account.getAccountName());
            pstmt.setString(3, account.getUsername());
            pstmt.setString(4, account.getPassword());
            pstmt.setString(5, account.getPhoneNumber());
            pstmt.setString(6, account.getEmail());
            pstmt.setString(7, account.getLocation());

            int numberOfRecordsAdded = pstmt.executeUpdate();
            return numberOfRecordsAdded;
        }
    }

    public static Employer getEmployerByUsername(String username) throws SQLException {
        try(Connection connection = createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return new Employer(rs.getInt("id"),
                        (rs.getString("accounttype")),
                        (rs.getString("accountname")),
                        (rs.getString("username")),
                        (rs.getString("password")),
                        (rs.getString("phoneNumber")),
                        (rs.getString("email")),
                        (rs.getString("location")));
            } else {
                return null;
            }
        }
    }

    public static int editEmployer(EditProfile profile) throws SQLException {

        try (Connection connection = createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE users SET accountname=?, password=?, phonenumber=?, email=?, location=?" +
                    "WHERE username = ?");

            pstmt.setString(6, profile.getUsername());
            pstmt.setString(1, profile.getAccountName());
            pstmt.setString(2, profile.getPassword());
            pstmt.setString(3, profile.getPhoneNumber());
            pstmt.setString(4, profile.getEmail());
            pstmt.setString(5, profile.getLocation());

            int numberOfRecordsEdited = pstmt.executeUpdate();
            return numberOfRecordsEdited;
        }
    }

    public static int removeEmployerUsingCredentials(String email, String password) throws SQLException, IOException {

        try (Connection connection = createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM users WHERE email = ? AND password = ?");

            pstmt.setString(1, email);
            pstmt.setString(2, password);

            int numberOfRecordsRemoved = pstmt.executeUpdate();
            return numberOfRecordsRemoved;
        }
    }

}
