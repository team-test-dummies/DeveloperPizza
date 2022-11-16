package com.revature.dao;

import com.revature.PrototypingApp;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class UserDaoTest {
    @BeforeTest
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterTest
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }
    @Test
    public void getAllCustomersPositiveHackTest() throws SQLException, IOException {
        //Arrange
        int expected = 4;

        //Act
        int actual = UserDao.getAllCustomers().size();

        //Assert
        //Assert.assertEquals(actual, expected);

    }
}