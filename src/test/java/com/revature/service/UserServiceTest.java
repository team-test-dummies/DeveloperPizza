package com.revature.service;

import com.revature.PrototypingApp;

import com.revature.dao.UserDao;
import com.revature.data.enums.exception.AccountUnsuccessfullyEditedException;
import com.revature.data.enums.exception.AccountUnsuccessfullyRemovedException;
import com.revature.data.enums.exception.UserNotFoundException;
import com.revature.data.records.Customer;
import com.revature.data.records.DeleteAccountInfo;
import com.revature.data.records.EditProfile;
import com.revature.data.records.RegisterInfo;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
    @Test(enabled = false)
    public void getAllCustomersTest() throws SQLException, IOException {

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

    @Test(expectedExceptions = com.revature.data.enums.exception.UserUnsuccessfullyAddedException.class)
    public void registerCustomerTestNegative() throws SQLException {

        RegisterInfo noInfo = new RegisterInfo(
                "CUSTOMER",
                "John Doe",
                "",
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

    @Test(expectedExceptions = com.revature.data.enums.exception.UserNotFoundException.class)
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

    @Test(expectedExceptions = com.revature.data.enums.exception.AccountUnsuccessfullyEditedException.class)
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
    public void removeCustomerTestPositive() throws SQLException, IOException { // NEED DELETE ORDERS METHOD IN ORDER TO DELETE USER

        DeleteAccountInfo credentials = new DeleteAccountInfo(
                "madkor436@company.net",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��"
        );

        int expected = 1;
        int actual = UserService.removeCustomerUsingCredentials(credentials);
        Assert.assertEquals(actual, expected);
    }

    @Test(expectedExceptions = com.revature.data.enums.exception.AccountUnsuccessfullyRemovedException.class)
    public void removeCustomerTestNegative() throws SQLException, IOException {

        DeleteAccountInfo credentials = new DeleteAccountInfo(
                "invalid",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��"
        );
        Exception exception = new AccountUnsuccessfullyRemovedException("Profile was not removed");

        Exception expected = exception;
        int actual = UserService.removeCustomerUsingCredentials(credentials);
        Assert.assertEquals(actual, expected);
    }
}
