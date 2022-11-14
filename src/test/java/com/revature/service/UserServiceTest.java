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
    public void authenticateTestAsEmployer() throws SQLException, AuthorizationException {
        Credentials credentials = new Credentials("employer", "\u000F�lQ\u0014B�I�˭3y�G�YNd��v C'\u0012SOn�!R");
        Authority expected = new Authority(7, "employer");
        Authority actual = UserService.authenticate(credentials);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void authenticateTestAsDeveloper() throws SQLException, AuthorizationException {
        Credentials credentials = new Credentials("developer", "\u0084�t��K\u000E�1\u0014�-:pf�b���\u000BNޅ\u0006�\u0019�\u0018x�");
        Authority expected = new Authority(6, "developer");
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
        try {
            Credentials credentials = new Credentials("username", "password");
            Credentials expected = new Credentials("username", "�\u0017-���;�KUy�\u001D�Z���t\u0011\uDB61\uDC99Vq\"Z%�J�");
            Credentials actual = UserService.sanitize(credentials);
            Assert.assertEquals(actual, expected);
        }
        catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void sanitizePasswordRemainsUnchangedTest() {
        Credentials credentials = new Credentials("Unsanitized Username", "Unsanitize-able Password");
        Credentials expected = new Credentials("unsanitized username", "�`�\u0004�&a��&�<\u000E/�\u0001\u0004\u0007\u001E$�+|��?Vc\u0010s�~");
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
        Credentials credentials = new Credentials("Unsanitized Username", "sanitized password");
        Credentials expected = new Credentials("unsanitized username", "w\u001D��!�B�#/��<`-\"r���s͇\u0019�g�$\u0012�4�");
        Credentials actual = null;
        try {
            actual = UserService.sanitize(credentials);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(actual, expected);
    }

}
