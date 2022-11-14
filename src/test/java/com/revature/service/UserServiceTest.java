package com.revature.service;

import com.revature.PrototypingApp;
import com.revature.exception.AuthorizationException;
import com.revature.exception.ValidationException;
import com.revature.records.Authority;
import com.revature.records.Credentials;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLException;

// depends on UserDaoTests
public class UserServiceTest {

    @BeforeTest
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterTest
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    // authenticate tests
    @Test
    public void authenticateTestAsEmployer() throws SQLException, AuthorizationException {
        Credentials credentials = new Credentials("employer username", "guest");
        Authority expected = new Authority(6, "employer");
        Authority actual = UserService.authenticate(credentials);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void authenticateTestAsDeveloper() throws SQLException, AuthorizationException {
        Credentials credentials = new Credentials("developer username", "guest");
        Authority expected = new Authority(7, "developer");
        Authority actual = UserService.authenticate(credentials);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void authenticateNegativeTest() throws SQLException {
        Credentials invalidCredentials = new Credentials("non-username", "non-password");
        Assert.assertThrows(
            AuthorizationException.class,
            () -> {
                UserService.authenticate(invalidCredentials);
            }
        );
    }

    // validate tests
    @Test
    public void validatePositiveTest() {
        Credentials credentials = new Credentials("ValId UsERName", "VaLid PASSword");
        try {
            UserService.validate(credentials);
        } catch (ValidationException e) {
            Assert.fail("expected credentials to be validated, but credentials were not validated");
        }
    }

    @Test
    public void validateNegativeUsernameTest() {
        Credentials invalidCredentials = new Credentials("", "valid");
        Assert.assertThrows(
                ValidationException.class,
                () -> UserService.validate(invalidCredentials)
        );
    }

    @Test
    public void validateNegativePasswordTest() {
        Credentials invalidCredentials = new Credentials("valid", "");
        Assert.assertThrows(
                ValidationException.class,
                () -> UserService.validate(invalidCredentials)
        );

    }

    @Test
    public void validateNegativeTest() {
        Credentials invalidCredentials = new Credentials("", "");
        Assert.assertThrows(
                ValidationException.class,
                () -> UserService.validate(invalidCredentials)
        );
    }

    // sanitize tests
    @Test
    public void sanitizePositiveTest() {
        Credentials credentials = new Credentials("sanitized username", "sanitized password");
        Credentials actual = UserService.sanitize(credentials);
        Assert.assertEquals(credentials, actual);
    }

    @Test
    public void sanitizePasswordRemainsUnchangedTest() {
        Credentials credentials = new Credentials("Unsanitized Username", "Unsanitize-able Password");
        Credentials expected = new Credentials("unsanitized username", "Unsanitize-able Password");
        Credentials actual = UserService.sanitize(credentials);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void sanitizeNegativeUsernameTest() {
        Credentials credentials = new Credentials("Unsanitized Username", "sanitized password");
        Credentials expected = new Credentials("unsanitized username", "sanitized password");
        Credentials actual = UserService.sanitize(credentials);
        Assert.assertEquals(expected, actual);
    }

}
