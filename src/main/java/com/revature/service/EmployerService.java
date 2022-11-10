package com.revature.service;

import com.revature.dao.EmployerDao;
import com.revature.model.Employer;

import java.sql.SQLException;
import java.util.List;

public class EmployerService {
    private EmployerDao employerDao = new EmployerDao();

    public List<Employer> getAllEmployers() throws SQLException {
        return employerDao.getAllEmployers();
    }
}
