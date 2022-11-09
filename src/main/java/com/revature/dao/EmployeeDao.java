package com.revature.dao;

import com.revature.models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    // Query DB for Employees
    public List<Employee> getAllEmployees() throws SQLException {
        try(Connection connection = ConnectionFactory.createConnection()) {
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM employees");
            ResultSet rs = pstmt.executeQuery();
            List<Employee> allEmployees = new ArrayList<>();

            // Employee Record --> Employee Object
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName    = rs.getString("first_name");
                String lastName     = rs.getString("last_name");

                Employee employee = new Employee(id, firstName, lastName);

                allEmployees.add(employee); // Store Employee in ArrayList
            }
            return allEmployees;
        }
    }
}
