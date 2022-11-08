package com.revature.daos;

import com.revature.models.Developer;
import com.revature.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDao {
    // Query DB for Developer
    public List<Developer> getAllDevelopers() throws SQLException {
        try(Connection connection = ConnectionFactory.createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM developer");
            ResultSet rs = pstmt.executeQuery();
            List<Developer> allDeveloper = new ArrayList<>();

            // Employee Record --> Employee Object
            while (rs.next()) {
                String firstName        = rs.getString("first_name");
                String lastName         = rs.getString("last_name");
                String location         = rs.getString("location");
                String availability     = rs.getString("availability");
                int salary              = rs.getInt("salary");
                String education        = rs.getString("education");
                String certifications   = rs.getString("certifications");
                String experience       = rs.getString("experience");
                String skillset         = rs.getString("skillset");
                String languages        = rs.getString("languages");
                String tools            = rs.getString("tools");
                String hobbies          = rs.getString("hobbies");
                String resume           = rs.getString("resume");

                Developer developer = new Developer(firstName, lastName, location, availability, salary, education, certifications, experience, skillset, languages, tools, hobbies, resume);

                allDeveloper.add(developer); // Store Employee in ArrayList
            }
            return allDeveloper;
        }
    }
}
