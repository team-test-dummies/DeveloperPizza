package com.revature.service;

import com.revature.dao.EmployeeDao;
import com.revature.model.Employee;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    private EmployeeDao employeeDao = new EmployeeDao();

    public List<Employee> getAllEmployees() throws SQLException {
        return employeeDao.getAllEmployees();
    }
}
