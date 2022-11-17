package com.revature.dao;

import com.revature.PrototypingApp;
import com.revature.data.enums.Role;
import com.revature.data.records.Credentials;
import com.revature.data.records.User;
import org.testng.Assert;
import org.testng.annotations.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class AuthDaoTest {

    @BeforeMethod
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterMethod
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    @DataProvider(name = "oneOfEachRole")
    private static Iterator<Object[]> oneUserOfEachRole() throws SQLException {
        PrototypingApp.setup();
        try (Connection connection = AuthDao.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users;"
            );
            ResultSet results = statement.executeQuery();
            List<User> users = User.parseAll(results);
            List<Role> roles = users
                    .parallelStream()
                    .map(user -> user.accountType())
                    .distinct()
                    .toList();
            PrototypingApp.cleanup();
            return roles.stream().map(role -> {
                for (User user : users) {
                    if (
                        user.accountType().equals(role)
                    ) return new Object[] {user};
                }
                throw new Error("user disappeared");
            }).iterator();
        }
    }

    // should use a paramerterized test that fetches from the database
    @Test(dataProvider = "oneOfEachRole")
    public void findUserPositive(User expected) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        User actual = AuthDao.findUser(new Credentials(
                expected.userName(),
                "guest" // all pre-built passwords are guest
        ));
        Assert.assertEquals(actual, expected);
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
    public void findUserNegative(Credentials fakeCredentials) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        User actual = AuthDao.findUser(fakeCredentials);
        Assert.assertEquals(actual, null);
    }
}
