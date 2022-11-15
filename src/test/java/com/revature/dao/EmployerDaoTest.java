package com.revature.dao;

import com.revature.PrototypingApp;
import com.revature.dto.RegisterInfo;
import com.revature.model.Employer;
import com.revature.records.UserDto;
import com.revature.service.EmployerService;
import org.h2.command.Prepared;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class EmployerDaoTest {
    @BeforeTest
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterTest
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }
    @Test
    public void getAllEmployersPositiveHackTest() throws SQLException, IOException {
        //Arrange
        int expected = 4;

        //Act
        int actual = EmployerDao.getAllEmployers().size();

        //Assert
        //Assert.assertEquals(actual, expected);

    }
}