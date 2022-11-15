package com.revature.service;

import com.revature.PrototypingApp;
import com.revature.enums.Role;
import com.revature.exception.AuthorizationException;
import com.revature.exception.ValidationException;
import com.revature.records.Authority;
import com.revature.records.Credentials;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
    public void authenticateTestAsEmployer() throws SQLException, AuthorizationException, NoSuchAlgorithmException, InvalidKeySpecException {
        Credentials credentials = new Credentials("employer", "guest");
        Authority expected = new Authority(7, Role.CUSTOMER);
        Authority actual = UserService.authenticate(credentials);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void authenticateTestAsDeveloper() throws SQLException, AuthorizationException, NoSuchAlgorithmException, InvalidKeySpecException {
        Credentials credentials = new Credentials("developer", "guest");
        Authority expected = new Authority(6, Role.STAFF);
        Authority actual = UserService.authenticate(credentials);
        Assert.assertEquals(actual, expected);
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
        Credentials credentials = new Credentials("ValIdUsERName", "VaLid PASSword");
        try {
            UserService.validate(credentials);
        } catch (ValidationException e) {
            Assert.fail("expected credentials to be validated, but credentials were not validated");
        }
    }

    @Test
    public void validateNoSpacesInsideUsernameEnforced() {
        throw new Error("unimplemented");
    }

    @Test
    public void validateOnlyWordCharactersEnforced() {
        throw new Error("unimplemented");
    }

    @Test
    public void valdate16CharactersEnforced() {
        throw new Error("unimplemented");
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
        try {
            Credentials credentials = new Credentials("username", "password");
            Credentials expected = new Credentials("username", "password");
            Credentials actual = UserService.sanitize(credentials);
            Assert.assertEquals(actual, expected);
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void sanitizePasswordRemainsUnchangedTest() {
        Credentials credentials = new Credentials("UnsanitizedUsername", "Unsanitize-able Password");
        Credentials expected = new Credentials("unsanitizedusername", "Unsanitize-able Password");
        Credentials actual = null;
        try {
            actual = UserService.sanitize(credentials);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void sanitizeNegativeUsernameTest() {
        Credentials credentials = new Credentials("UnsanitizedUsername", "sanitized password");
        Credentials expected = new Credentials("unsanitizedusername", "sanitized password");
        Credentials actual = null;
        try {
            actual = UserService.sanitize(credentials);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(actual, expected);
    }

}
