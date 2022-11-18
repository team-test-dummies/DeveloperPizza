package com.revature.service;

import com.revature.PrototypingApp;
import com.revature.data.enums.Role;
import com.revature.data.exception.AuthorizationException;
import com.revature.data.exception.ValidationException;
import com.revature.data.records.Authority;
import com.revature.data.records.Credentials;
import org.testng.Assert;
import org.testng.annotations.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

// depends on UserDaoTests
public class AuthServiceTest {

    @BeforeMethod
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterMethod
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    // authenticate tests
    @Test
    public void authenticateTestAsEmployer() throws SQLException, AuthorizationException, NoSuchAlgorithmException, InvalidKeySpecException {
        Credentials credentials = new Credentials("employer", "guest");
        Authority expected = new Authority(7, Role.CUSTOMER);
        Authority actual = AuthService.authenticate(credentials);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void authenticateTestAsDeveloper() throws SQLException, AuthorizationException, NoSuchAlgorithmException, InvalidKeySpecException {
        Credentials credentials = new Credentials("developer", "guest");
        Authority expected = new Authority(6, Role.STAFF);
        Authority actual = AuthService.authenticate(credentials);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void authenticateNegativeTest() throws SQLException {
        Credentials invalidCredentials = new Credentials("non-username", "non-password");
        Assert.assertThrows(
            AuthorizationException.class,
            () -> {
                AuthService.authenticate(invalidCredentials);
            }
        );
    }

    // validate tests
    @Test
    public void validatePositiveTest() {
        Credentials credentials = new Credentials("ValIdUsERName", "VaLid PASSword");
        try {
            AuthService.validate(credentials);
        } catch (ValidationException e) {
            Assert.fail("expected credentials to be validated, but credentials were not validated");
        }
    }

    @Test
    public void validateNoSpacesInsideUsernameEnforced() {
        Credentials credentials = new Credentials("invalid username", "pasword");
        Assert.assertThrows(ValidationException.class, () -> {
           AuthService.validate(credentials);
        });
    }

    @Test
    public void validateOnlyWordCharactersEnforced() {
        Credentials credentials = new Credentials("<script>xss</script>", "password");
        Assert.assertThrows(ValidationException.class, () -> {
            AuthService.validate(credentials);
        });
    }

    @Test
    public void valdate16CharactersEnforced() {
        // this username is longer than the 16 character limit
        Credentials credentials = new Credentials("01234567890123456789", "password");
        Assert.assertThrows(ValidationException.class, () -> {
           AuthService.validate(credentials);
        });
    }

    @Test
    public void validateNegativeUsernameTest() {
        Credentials invalidCredentials = new Credentials("", "valid");
        Assert.assertThrows(
                ValidationException.class,
                () -> AuthService.validate(invalidCredentials)
        );
    }

    @Test
    public void validateNegativePasswordTest() {
        Credentials invalidCredentials = new Credentials("valid", "");
        Assert.assertThrows(
                ValidationException.class,
                () -> AuthService.validate(invalidCredentials)
        );

    }

    @Test
    public void validateNegativeTest() {
        Credentials invalidCredentials = new Credentials("", "");
        Assert.assertThrows(
                ValidationException.class,
                () -> AuthService.validate(invalidCredentials)
        );
    }

    // sanitize tests
    @Test
    public void sanitizePositiveTest() {
        try {
            Credentials credentials = new Credentials("username", "password");
            Credentials expected = new Credentials("username", "password");
            Credentials actual = AuthService.sanitize(credentials);
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
            actual = AuthService.sanitize(credentials);
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
            actual = AuthService.sanitize(credentials);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
        Assert.assertEquals(actual, expected);
    }

}
