package com.revature.service;

import com.revature.dao.EmployerDao;
import com.revature.dao.UserDao;
import com.revature.data.records.EditProfile;
import com.revature.data.records.Employer;
import com.revature.data.records.RegisterInfo;
import com.revature.dto.EditProfile;
import com.revature.dto.RegisterInfo;
import com.revature.exception.AccountUnsuccessfullyEditedException;
import com.revature.exception.AccountUnsuccessfullyRemovedException;
import com.revature.exception.UserNotFoundException;
import com.revature.exception.UserUnsuccessfullyAddedException;
import com.revature.model.Employer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployerService {
    public static List<Employer> getAllEmployers() throws SQLException, IOException {
        return UserDao.getAllEmployers();
    }

    public static int registerEmployer(RegisterInfo account) throws SQLException {

        account.setAccountType(account.getAccountType().strip());
        account.setAccountName(account.getAccountName().strip());
        account.setUsername(account.getUsername().strip());
        account.setPassword(account.getPassword().strip());
        account.setPhoneNumber(account.getPhoneNumber().strip());
        account.setEmail(account.getEmail().strip());
        account.setLocation(account.getLocation().strip());

        int recordsAdded = UserDao.registerEmployer(account); // 1 if a user was added, 0 if no user was added

        if (recordsAdded != 1) {
            throw new com.revature.data.enums.exception.UserUnsuccessfullyAddedException("Account was not created");
        } return recordsAdded;
    }

    public static Employer getEmployerByUsername(String username) throws SQLException {
        Employer employer = UserDao.getEmployerByUsername(username);
        if (employer == null) {
            throw new com.revature.data.enums.exception.UserNotFoundException("User does not exist");
        } else {
            return employer;
        }
    }

    public static EditProfile editEmployer(EditProfile profile) throws SQLException, IOException {

        int recordsEdited = UserDao.editEmployer(profile);

        if (recordsEdited != 1) {
            throw new com.revature.data.enums.exception.AccountUnsuccessfullyEditedException("Profile was not edited");
        }

        return profile;
    }

    public static void removeEmployerUsingCredentials(String email, String password) throws SQLException, IOException {

        int recordsRemoved = UserDao.removeEmployerUsingCredentials(email, password);

        if (recordsRemoved != 1) {
            throw new com.revature.data.enums.exception.AccountUnsuccessfullyRemovedException("Profile was not removed");
        }
    }
}
