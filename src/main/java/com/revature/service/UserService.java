package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.data.records.Customer;
import com.revature.data.records.DeleteAccountInfo;
import com.revature.data.records.EditProfile;
import com.revature.data.records.RegisterInfo;
import com.revature.data.enums.exception.AccountUnsuccessfullyEditedException;
import com.revature.data.enums.exception.AccountUnsuccessfullyRemovedException;
import com.revature.data.enums.exception.UserNotFoundException;
import com.revature.data.enums.exception.UserUnsuccessfullyAddedException;


import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    public static List<Customer> getAllCustomers() throws SQLException, IOException {
        return UserDao.getAllCustomers();
    }

    public static int registerCustomer(RegisterInfo account) throws SQLException {

        int recordsAdded = UserDao.registerCustomer(account); // 1 if a user was added, 0 if no user was added

        if (recordsAdded != 1) {
            throw new UserUnsuccessfullyAddedException("Account was not created");
        }
        else if (account.getAccountName().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Account was not created");
        }
        else if (account.getUsername().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Account was not created");
        }
        else if (account.getPassword().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Account was not created");
        }
        else if (account.getPhoneNumber().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Account was not created");
        }
        else if (account.getEmail().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Account was not created");
        }
        else if (account.getLocation().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Account was not created");
        }
        return recordsAdded;
    }

    public static Customer getCustomerByUsername(String username) throws SQLException {
        Customer customer = UserDao.getCustomerByUsername(username);
        if (customer == null) {
            throw new UserNotFoundException("User does not exist");
        } else {
            return customer;
        }
    }

    public static EditProfile editCustomer(EditProfile profile) throws SQLException, IOException {

        int recordsEdited = UserDao.editCustomer(profile);

        if (recordsEdited != 1) {
            throw new AccountUnsuccessfullyEditedException("Profile was not edited");
        }
        return profile;
    }

    public static int removeCustomerUsingCredentials(DeleteAccountInfo credentials) throws SQLException, IOException {

        int recordsRemoved = UserDao.removeCustomerUsingCredentials(credentials);

        if (recordsRemoved != 1) {
            throw new AccountUnsuccessfullyRemovedException("Profile was not removed");
        }
        return recordsRemoved;
    }
}
