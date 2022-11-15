package com.revature.service;

import com.revature.PrototypingApp;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class UserServiceTest {

    @BeforeTest
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterTest
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    @Test
    public void getAllEmployersTest() {
        //Arrange

        //Act

        //Assert

    }

    @Test
    public void registerEmployerTest() throws SQLException {

    }

    @Test
    public void getEmployerByUsernameTest() throws SQLException {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void editEmployerTest() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    public void removeEmployerTest() {
        //Arrange

        //Act

        //Assert
    }
}
