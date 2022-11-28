package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.data.records.Customer;
import com.revature.data.records.DeleteAccountInfo;
import com.revature.data.records.EditProfile;
import com.revature.data.records.RegisterInfo;
import com.revature.data.exception.AccountUnsuccessfullyEditedException;
import com.revature.data.exception.AccountUnsuccessfullyRemovedException;
import com.revature.data.exception.UserNotFoundException;
import com.revature.data.exception.UserUnsuccessfullyAddedException;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import static com.revature.service.AuthService.solidUsername;

public class UserService {
    private static Pattern solidUsername = Pattern.compile("^\\s*[A-Za-z0-9]+\\s*$");
    public static List<Customer> getAllCustomers() throws SQLException, IOException {
        return UserDao.getAllCustomers();
    }

    public static int registerCustomer(RegisterInfo account) throws SQLException {

        int recordsAdded = UserDao.registerCustomer(account); // 1 if a user was added, 0 if no user was added

        if (recordsAdded != 1) {
            throw new UserUnsuccessfullyAddedException("Account was not created");
        }else if (account.getAccountType().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Account type required.");
        }
        else if (account.getAccountName().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Full name required.");
        }
        else if (account.getUsername().length() == 0 || account.getUsername().length() > 16 ||
                !solidUsername.matcher(account.getUsername()).find()) {
            throw new UserUnsuccessfullyAddedException("Valid username required.");
        }
        else if (account.getPassword().length() == 0 || account.getPassword().length() > 16) {
            throw new UserUnsuccessfullyAddedException("Valid password required.");
        }
        else if (account.getPhoneNumber().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Phone number required.");
        }
        else if (account.getEmail().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Valid email required.");
        }
        else if (account.getLocation().length() == 0) {
            throw new UserUnsuccessfullyAddedException("Location required.");
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

    public static Customer getUserById(int id) throws SQLException {
        Customer customer = UserDao.getUserById(id);
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

    public static int removeCustomerUsingCredentials(DeleteAccountInfo credentials) throws SQLException, IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        int recordsRemoved = UserDao.removeCustomerUsingCredentials(credentials);

        if (recordsRemoved != 1) {
            throw new AccountUnsuccessfullyRemovedException("Profile was not removed");
        }
        return recordsRemoved;
    }
}
