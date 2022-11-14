package com.revature.dao;

import com.revature.PrototypingApp;
import com.revature.records.Credentials;
import com.revature.records.UserDto;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class UserDaoTest {

    @BeforeTest
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterTest
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    @DataProvider(name = "oneOfEachRole")
    private static Iterator<Object[]> oneUserOfEachRole() {
        try (Connection connection = UserDao.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users;"
            );
            ResultSet results = statement.executeQuery();
            List<UserDto> users = UserDto.parseAll(results);
            List<String> roles = users
                    .parallelStream()
                    .map(user -> user.accountType())
                    .distinct()
                    .toList();
            return roles.stream().map(role -> {
                for (UserDto user : users) {
                    if (
                        user.accountType().equals(role)
                    ) return new Object[] {user};
                }
                throw new Error("user disappeared");
            }).iterator();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // should use a paramerterized test that fetches from the database
    @Test(dataProvider = "oneOfEachRole")
    public void findUserPositive(UserDto expected) throws SQLException {
        UserDto actual = UserDao.findUser(new Credentials(
                expected.userName(),
                expected.password()
        ));
        Assert.assertEquals(expected, actual);
    }

    @DataProvider(name="fakeCredentials")
    public Object[][] fakeCredentials() {
        return new Credentials[][] {
            {new Credentials("","")},
            {new Credentials("fake", "")},
            {new Credentials("", "fake")},
            {new Credentials("fake", "fake")},
            {new Credentials("fake", "guest")},
            {new Credentials("employer username", "fake")}
        };
    }

    @Test(dataProvider = "fakeCredentials")
    public void findUserNegative(Credentials fakeCredentials) throws SQLException {
        UserDto actual = UserDao.findUser(fakeCredentials);
        Assert.assertEquals(null, actual);
    }
}
