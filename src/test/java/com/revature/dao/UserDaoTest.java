package com.revature.dao;

import com.revature.PrototypingApp;
import com.revature.records.UserDto;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
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

    // should use a paramerterized test that fetches from the database
    @Test
    public void findUserByCredentialsPositiveEmployer() throws SQLException {
        UserDto expected = new UserDto(
                6,
                "employer",
                "employer name",
                "employer username",
                "guest",
                "000-000-0000",
                "employer.username@example.com",
                "Minnesota"
        );
        UserDto user = UserDao.findUserByCredentials("employer username", "guest");
        Assert.assertEquals(expected, user);
    }

    @Test
    public void findUserByCredentialsNegativeUsername() throws SQLException {
        UserDto expected = null;
        UserDto user = UserDao.findUserByCredentials("non-existant username", "guest");
        Assert.assertEquals(expected, user);
    }

    @Test
    public void findUserByCredentialsNegativePassword() throws SQLException {
        UserDto expected = null;
        UserDto user = UserDao.findUserByCredentials("employer username", "non-existant password");
        Assert.assertEquals(expected, user);
    }

    @Test
    public void findUserByCredentialsNegative() throws SQLException {
        UserDto expected = null;
        UserDto user = UserDao.findUserByCredentials("non-existant username", "non-existant password");
        Assert.assertEquals(expected, user);
    }

    // should use a paramerterized test that fetches from the database
    @Test
    public void findUserByCredentialsPositiveDeveloper() throws SQLException {
        UserDto expected = new UserDto(
                7,
                "developer",
                "developer name",
                "developer username",
                "guest",
                "000-000-0000",
                "developer.username@example.com",
                "Minnesota"
        );
        UserDto user = UserDao.findUserByCredentials("developer username", "guest");
        Assert.assertEquals(expected, user);
    }

}
