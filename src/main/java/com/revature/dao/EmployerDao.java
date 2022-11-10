package com.revature.dao;


import com.revature.model.Employer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployerDao {
    // Query DB for Employers
    public List<Employer> getAllEmployers() throws SQLException {
        try(Connection connection = ConnectionFactory.createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM employers");
            ResultSet rs = pstmt.executeQuery();
            List<Employer> allEmployers = new ArrayList<>();

            // Employer Record --> Employer Object
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName    = rs.getString("first_name");
                String lastName     = rs.getString("last_name");

                Employer employer = new Employer(id, firstName, lastName);

                allEmployers.add(employer); // Store Employer in ArrayList
            }
            return allEmployers;
        }
    }
}
