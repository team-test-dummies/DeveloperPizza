package com.revature.dao;

import com.revature.PrototypingApp;
import com.revature.data.enums.exception.UserUnsuccessfullyAddedException;
import com.revature.data.records.Customer;
import com.revature.data.records.RegisterInfo;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.SQLException;

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
    public void getAllCustomersPositiveHackTest() throws SQLException, IOException {
        //Arrange

        int expected = 4;

        //Act
        int actual = UserDao.getAllCustomers().size();

        //Assert
        //Assert.assertEquals(actual, expected);

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
                "Atlanta, GA");

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
                "Atlanta, GA");

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
                "New Mexico");
        Customer actual = UserDao.getCustomerByUsername(username);

        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getUserNegative() {

    }
}