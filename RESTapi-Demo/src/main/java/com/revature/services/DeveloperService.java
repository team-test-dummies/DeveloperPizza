package com.revature.services;

import com.revature.daos.DeveloperDao;
import com.revature.models.Developer;

import java.sql.SQLException;
import java.util.List;

public class DeveloperService {
    private DeveloperDao developerDao = new DeveloperDao();

    public List<Developer> getAllDevelopers() throws SQLException {
        return developerDao.getAllDevelopers();
    }
}
