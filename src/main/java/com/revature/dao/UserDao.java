package com.revature.dao;


import com.revature.data.records.*;
import com.revature.service.AuthService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserDao extends Dao {
    private static Pattern solidUsername = Pattern.compile("^\\s*[A-Za-z0-9]+\\s*$");
    public static List<Customer> getAllCustomers() throws SQLException, IOException {
        try(Connection connection = createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE accountType = 'CUSTOMER'");

            ResultSet rs = pstmt.executeQuery();
            List<Customer> allCustomers = new ArrayList<>();

            // Customer Record --> Customer Object
            while (rs.next()) {
                int id = rs.getInt("id");
                String accountType = rs.getString("accountType");
                String accountName = rs.getString("accountName");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                String location = rs.getString("location");

                Customer c = new Customer(id, accountType, accountName, username, password, phoneNumber, email, location);

                allCustomers.add(c); // Store Employer in ArrayList
            }
            return allCustomers;
        }
    }

    public static int registerCustomer(RegisterInfo account) throws SQLException {
        try (Connection connection = createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO users (accountType, accountName, username, password, phoneNumber, email, location) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );

            String hashedPassword = AuthService.quickhash(account.getUsername(), account.getPassword());
            pstmt.setString(1, account.getAccountType().strip());
            pstmt.setString(2, account.getAccountName().strip());
            pstmt.setString(3, account.getUsername().strip());
            pstmt.setString(4, hashedPassword);
            pstmt.setString(5, account.getPhoneNumber().strip());
            pstmt.setString(6, account.getEmail().strip());
            pstmt.setString(7, account.getLocation().strip());

            int numberOfRecordsAdded = pstmt.executeUpdate();
            if (account.getAccountType().length() == 0) {
                numberOfRecordsAdded = 0;
            } else if (account.getAccountName().length() == 0) {
                numberOfRecordsAdded = 0;
            } else if (account.getUsername().length() == 0 || account.getUsername().length() < 3 || account.getUsername().length() > 16
                        || !solidUsername.matcher(account.getUsername()).find()) {
                numberOfRecordsAdded = 0;
            } else if (account.getPassword().length() == 0 || account.getPassword().length() < 3 || account.getPassword().length() > 16) {
                numberOfRecordsAdded = 0;
            } else if (account.getPhoneNumber().length() == 0) {
                numberOfRecordsAdded = 0;
            } else if (account.getEmail().length() == 0) {
                numberOfRecordsAdded = 0;
            } else if (account.getLocation().length() == 0) {
                numberOfRecordsAdded = 0;
            } return numberOfRecordsAdded;

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static Customer getCustomerByUsername(String username) throws SQLException {
        try(Connection connection = createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return new Customer(rs.getInt("id"),
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

    public static Customer getUserById(int id) throws SQLException {
        try(Connection connection = createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return new Customer(rs.getInt("id"),
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
    public static int editCustomer(EditProfile profile) throws SQLException {

        try (Connection connection = createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("UPDATE users SET accountname=?, password=?, phonenumber=?, email=?, location=?" +
                    "WHERE username = ?");

            pstmt.setString(1, profile.getAccountName());
            pstmt.setString(2, profile.getPassword());
            pstmt.setString(3, profile.getPhoneNumber());
            pstmt.setString(4, profile.getEmail());
            pstmt.setString(5, profile.getLocation());
            pstmt.setString(6, profile.getUsername());

            int numberOfRecordsEdited = pstmt.executeUpdate();
            return numberOfRecordsEdited;
        }
    }

    public static int removeCustomerUsingCredentials(DeleteAccountInfo credentials) throws SQLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        String hashedPassword = AuthService.quickhash(credentials.getUsername(), credentials.getPassword());
        try (Connection connection = createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("DELETE FROM users WHERE username = ? AND password = ?");

            pstmt.setString(1, credentials.getUsername());
            pstmt.setString(2, hashedPassword);

            int numberOfRecordsRemoved = pstmt.executeUpdate();
            return numberOfRecordsRemoved;
        }
    }

}
