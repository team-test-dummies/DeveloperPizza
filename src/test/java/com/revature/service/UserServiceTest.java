package com.revature.service;

import com.revature.PrototypingApp;

import com.revature.dao.Dao;
import com.revature.dao.UserDao;
import com.revature.data.exception.AccountUnsuccessfullyEditedException;
import com.revature.data.exception.AccountUnsuccessfullyRemovedException;
import com.revature.data.exception.UserNotFoundException;
import com.revature.data.exception.UserUnsuccessfullyAddedException;
import com.revature.data.records.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

public class UserServiceTest {

    @BeforeMethod
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterMethod
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    // VIEW ALL CUSTOMERS
    @Test
    public void getAllCustomersTest() throws SQLException, IOException {
        int actual = UserService.getAllCustomers().size();
        Assert.assertEquals(actual, 6);
    }

    // -- REGISTER CUSTOMERS --
    @Test
    public void registerCustomerTestPositive() throws SQLException {

        RegisterInfo newCustomerInfo = new RegisterInfo(
                "CUSTOMER",
                "John Doe",
                "jonnie",
                "password",
                "555-555-5555",
                "john@gmail.com",
                "Georgia"
        );

        int expected = 1;
        int actual = UserService.registerCustomer(newCustomerInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = UserUnsuccessfullyAddedException.class)

    public void registerCustomerNoUserameTestNegative() throws SQLException {

        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "",
                "jonnie",
                "password",
                "555-555-5555",
                "jogn@gmail.com",
                "Georgia"
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Account was not created");

        Exception expected = exception;
        int actual = UserService.registerCustomer(noInfo);
        Assert.assertEquals(actual, expected);
    }
    @Test(expectedExceptions = UserUnsuccessfullyAddedException.class)
    public void registerCustomerNoNameTestNegative() throws SQLException {

        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "",
                "john_doe",
                "password",
                "555-555-5555",
                "jogn@gmail.com",
                "Georgia"
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Account was not created");

        Exception expected = exception;
        int actual = UserService.registerCustomer(noInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = UserUnsuccessfullyAddedException.class)
    public void registerCustomerNoPassTestNegative() throws SQLException {

        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "John Doe",
                "john_doe",
                "",
                "555-555-5555",
                "jogn@gmail.com",
                "Georgia"
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Account was not created");

        Exception expected = exception;
        int actual = UserService.registerCustomer(noInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = UserUnsuccessfullyAddedException.class)
    public void registerCustomerNoEmailTestNegative() throws SQLException {

        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "John Doe",
                "john_doe",
                "password",
                "555-555-5555",
                "",
                "Georgia"
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Account was not created");
        Exception expected = exception;
        int actual = UserService.registerCustomer(noInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = UserUnsuccessfullyAddedException.class)
    public void registerCustomerNoPhoneTestNegative() throws SQLException {
        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "John Doe",
                "john_doe",
                "password",
                "",
                "john@gmail.com",
                "Georgia"
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Account was not created");

        Exception expected = exception;
        int actual = UserService.registerCustomer(noInfo);
        Assert.assertEquals(actual, expected);
    }
    @Test(expectedExceptions = UserUnsuccessfullyAddedException.class)
    public void registerCustomerNoLocationTestNegative() throws SQLException {

        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "John Doe",
                "john_doe",
                "password",
                "555-555-5555",
                "john@gmail.com",
                ""
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Account was not created");

        Exception expected = exception;
        int actual = UserService.registerCustomer(noInfo);
        Assert.assertEquals(actual, expected);
    }


    @Test(expectedExceptions = UserUnsuccessfullyAddedException.class)
    public void registerCustomerTestNegativePassword() throws SQLException {

        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "John Doe",
                "jonnie",
                "",
                "555-555-5555",
                "john@gmail.com",
                "Georgia"
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Password required.");

        Exception expected = exception;
        int actual = UserService.registerCustomer(noInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = UserUnsuccessfullyAddedException.class)
    public void registerCustomerTestNegativePhone() throws SQLException {

        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "John Doe",
                "jonnie",
                "password",
                "",
                "john@gmail.com",
                "Georgia"
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Phone number required.");

        Exception expected = exception;
        int actual = UserService.registerCustomer(noInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = UserUnsuccessfullyAddedException.class)
    public void registerCustomerTestNegativeEmail() throws SQLException {

        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "John Doe",
                "jonnie",
                "password",
                "555-555-5555",
                "",
                "Georgia"
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Email required.");

        Exception expected = exception;
        int actual = UserService.registerCustomer(noInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = UserUnsuccessfullyAddedException.class)
    public void registerCustomerTestNegative() throws SQLException {

        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "John Doe",
                "jonnie",
                "password",
                "555-555-5555",
                "john@gmail.com",
                ""
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Location required.");

        Exception expected = exception;
        int actual = UserService.registerCustomer(noInfo);
        Assert.assertEquals(actual, expected);
    }

    // -- VIEW CUSTOMER BY USERNAME --
    @Test
    public void getCustomerByUsernamePositive() throws SQLException {

        Customer madisonKora = new Customer(
                1,
                "CUSTOMER",
                "madison_kora",
                "madkor436",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��",
                "505-684-9399",
                "madkor436@company.net",
                "New Mexico"
        );

        Customer expected = madisonKora;
        Customer actual = UserService.getCustomerByUsername("madkor436");
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void getCustomerByUsernameNegative() throws SQLException {

        Customer customer = new Customer(1,
                "CUSTOMER",
                "madison_kora",
                "madkor436",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��",
                "505-684-9399",
                "madkor436@company.net",
                "New Mexico"
        );
        Exception exception = new UserNotFoundException("User does not exist");

        Exception expected = exception;
        Customer actual = UserService.getCustomerByUsername("invalidUsername");
        Assert.assertSame(actual, expected);
    }

    // -- EDIT CUSTOMER PROFILE --
    @Test
    public void editCustomerTestPositive() throws SQLException, IOException {

        EditProfile editedProfile = new EditProfile(
                "madison_kora",
                "madkor436",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��",
                "555-555-5555",
                "madkor436@company.net",
                "California"
        );

        EditProfile expected = editedProfile;
        EditProfile actual = UserService.editCustomer(editedProfile);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = AccountUnsuccessfullyEditedException.class)
    public void editCustomerTestNegative() throws SQLException, IOException {

        EditProfile editedProfile = new EditProfile(
                "madison_kora",
                "INVALID",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��",
                "555-555-5555",
                "madkor436@company.net",
                "California"
        );
        Exception exception = new AccountUnsuccessfullyEditedException("Profile was not edited");

        Exception expected = exception;
        EditProfile actual = UserService.editCustomer(editedProfile);
        Assert.assertEquals(actual, expected);
    }

    // -- DELETE CUSTOMER PROFILE --
    @Test
    public void removeCustomerTestPositive() throws SQLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException { // NEED DELETE ORDERS METHOD IN ORDER TO DELETE USER

        DeleteAccountInfo credentials = new DeleteAccountInfo(
                "madkor436",
                "guest"
        );

        int expected = 1;
        int actual = UserService.removeCustomerUsingCredentials(credentials);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = AccountUnsuccessfullyRemovedException.class)
    public void removeCustomerTestNegative() throws SQLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        DeleteAccountInfo credentials = new DeleteAccountInfo(
                "invalid",
                "guest"
        );
        Exception exception = new AccountUnsuccessfullyRemovedException("Profile was not removed");

        Exception expected = exception;
        int actual = UserService.removeCustomerUsingCredentials(credentials);
        Assert.assertEquals(actual, expected);
    }
}
