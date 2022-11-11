package com.revature.dao;


import com.revature.model.Employer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployerDao {
    public List<Employer> getAllEmployers() throws SQLException {
        try(Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM employers");
            ResultSet rs = pstmt.executeQuery();
            List<Employer> allEmployers = new ArrayList<>();

            while (rs.next()) {
                String firstName    = rs.getString("first_name");
                String lastName     = rs.getString("last_name");
                String username     = rs.getString("username");
                String password     = rs.getString("password");
                String phoneNumber  = rs.getString("000-000-0000");
                String email     = rs.getString("email@domain");

                Employer employer = new Employer(firstName, lastName, username, password, phoneNumber, email);

                allEmployers.add(employer);
            }
            return allEmployers;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
