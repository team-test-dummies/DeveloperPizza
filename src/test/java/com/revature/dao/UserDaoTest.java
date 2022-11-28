package com.revature.dao;

import com.revature.PrototypingApp;
import com.revature.data.exception.UserUnsuccessfullyAddedException;
import com.revature.data.records.*;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {
    @BeforeMethod
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterMethod
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }
    @Test
    public void getAllCustomersPositive() throws SQLException, IOException {
        throw new SkipException("unimplemented");
    }

    @Test
    public void registerUserPositive() throws SQLException {
        RegisterInfo newUser = new RegisterInfo(
                "CUSTOMER",
                "Jane Doe",
                "janejane",
                "password",
                "555-555-5555",
                "jane@gmail.com",
                "Atlanta, GA"
        );

        int expected = 1;
        int actual = UserDao.registerCustomer(newUser);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void registerUserNegative() throws SQLException {

        RegisterInfo invalidInfo = new RegisterInfo(
                "",
                "Jane Doe",
                "janejane",
                "password",
                "555-555-5555",
                "",
                "Atlanta, GA"
        );

        int expected = 0;
        int actual = UserDao.registerCustomer(invalidInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void registerUserLongUsername() throws SQLException {

        RegisterInfo invalidInfo = new RegisterInfo(
                "CUSTOMER",
                "Jane Doe",
                "thisusernameislongerthan16characters",
                "password",
                "555-555-5555",
                "jane@gmail.com",
                "Atlanta, GA"
        );

        int expected = 0;
        int actual = UserDao.registerCustomer(invalidInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void registerUserShortUsername() throws SQLException {

        RegisterInfo invalidInfo = new RegisterInfo(
                "CUSTOMER",
                "Jane Doe",
                "oo",
                "password",
                "555-555-5555",
                "jane@gmail.com",
                "Atlanta, GA"
        );

        int expected = 0;
        int actual = UserDao.registerCustomer(invalidInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void registerUserInvalidUsernameChar() throws SQLException {

        RegisterInfo invalidInfo = new RegisterInfo(
                "CUSTOMER",
                "Jane Doe",
                "@#!%^&*)(`+",
                "password",
                "555-555-5555",
                "jane@gmail.com",
                "Atlanta, GA"
        );

        int expected = 0;
        int actual = UserDao.registerCustomer(invalidInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void registerUserLongPassword() throws SQLException {

        RegisterInfo invalidInfo = new RegisterInfo(
                "CUSTOMER",
                "Jane Doe",
                "janejane",
                "thispasswordistoolong",
                "555-555-5555",
                "jane@gmail.com",
                "Atlanta, GA"
        );

        int expected = 0;
        int actual = UserDao.registerCustomer(invalidInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void registerUserShortPassword() throws SQLException {

        RegisterInfo invalidInfo = new RegisterInfo(
                "CUSTOMER",
                "Jane Doe",
                "janejane",
                "pa",
                "555-555-5555",
                "jane@gmail.com",
                "Atlanta, GA"
        );

        int expected = 0;
        int actual = UserDao.registerCustomer(invalidInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getUserPositive() throws SQLException {

        String username = "madkor436";
        Customer expected = new Customer(
                1,
                "CUSTOMER",
                "madison_kora",
                "madkor436",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��",
                "505-684-9399",
                "madkor436@company.net",
                "New Mexico"
        );
        Customer actual = UserDao.getCustomerByUsername(username);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getUserNegative() throws SQLException {
        String username = "invalid";
        Customer expected = null;
        Customer actual = UserDao.getCustomerByUsername(username);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void editUserPositive() throws SQLException {
        EditProfile editedInfo = new EditProfile(
                "madison_kora",
                "invalidUsername",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��",
                "505-684-9399",
                "madkor436@company.net",
                "New Mexico"
        );

        int expected = 0;
        int actual = UserDao.editCustomer(editedInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void editUserNegative() throws SQLException {
        EditProfile editedInfo = new EditProfile(
                "new_name",
                "new",
                "newPassword",
                "555-555-5555",
                "new@gmail.com",
                "New Mexico"
        );

        int expected = 0;
        int actual = UserDao.editCustomer(editedInfo);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void removeUserPositive() throws SQLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        DeleteAccountInfo validCredentials = new DeleteAccountInfo(
                "madkor436",
                "guest"
        );

        int expected = 1;
        int actual = UserDao.removeCustomerUsingCredentials(validCredentials);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void removeUserNegative() throws SQLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        DeleteAccountInfo invalidCredentials = new DeleteAccountInfo(
                "madkor436",
                "invalid"
        );

        int expected = 0;
        int actual = UserDao.removeCustomerUsingCredentials(invalidCredentials);
        Assert.assertEquals(actual, expected);
    }
}