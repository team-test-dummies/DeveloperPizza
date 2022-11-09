package com.revature.service;

import com.revature.daos.EmployeeDao;
import com.revature.models.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDao.getAllEmployees();
    }
}
