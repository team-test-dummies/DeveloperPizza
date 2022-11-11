package com.revature.service;

import com.revature.dao.DeveloperDao;
import com.revature.exception.LoginException;
import com.revature.model.Developer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DeveloperService {
    private DeveloperDao developerDao = new DeveloperDao();

    public List<Developer> getAllDevelopers() throws SQLException {
        return developerDao.getAllDevelopers();
    }


}
