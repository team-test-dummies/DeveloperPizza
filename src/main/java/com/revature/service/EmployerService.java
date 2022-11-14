package com.revature.service;

import com.revature.dao.EmployerDao;
import com.revature.dto.EditProfile;
import com.revature.dto.Message;
import com.revature.dto.RegisterInfo;
import com.revature.exception.AccountUnsuccessfullyEditedException;
import com.revature.exception.AccountUnsuccessfullyRemovedException;
import com.revature.exception.EmployerNotFoundException;
import com.revature.exception.EmployerUnsuccessfullyAddedException;
import com.revature.model.Employer;
import com.revature.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class EmployerService {
    public static List<Employer> getAllEmployers() throws SQLException, IOException {
        return EmployerDao.getAllEmployers();
    }

    public static void registerEmployer(RegisterInfo account) throws SQLException {

        account.setAccountType(account.getAccountType().strip());
        account.setAccountName(account.getAccountName().strip());
        account.setUsername(account.getUsername().strip());
        account.setPassword(account.getPassword().strip());
        account.setPhoneNumber(account.getPhoneNumber().strip());
        account.setEmail(account.getEmail().strip());
        account.setLocation(account.getLocation().strip());

        int recordsAdded = EmployerDao.registerEmployer(account); // 1 if a user was added, 0 if no user was added

        if (recordsAdded != 1) {
            throw new EmployerUnsuccessfullyAddedException("Account was not created");
        }
    }

    public static Employer getEmployerByUsername(String username) throws SQLException {
        Employer employer = EmployerDao.getEmployerByUsername(username);
        if (employer == null) {
            throw new EmployerNotFoundException("User does not exist");
        } else {
            return employer;
        }
    }

    public static void editEmployer(EditProfile profile) throws SQLException, IOException {

        int recordsEdited = EmployerDao.editEmployer(profile);

        if (recordsEdited != 1) {
            throw new AccountUnsuccessfullyEditedException("Profile was not edited");
        }
    }

    public static void removeEmployerUsingCredentials(String email, String password) throws SQLException, IOException {

        int recordsRemoved = EmployerDao.removeEmployerUsingCredentials(email, password);

        if (recordsRemoved != 1) {
            throw new AccountUnsuccessfullyRemovedException("Profile was not removed");
        }
    }
}
