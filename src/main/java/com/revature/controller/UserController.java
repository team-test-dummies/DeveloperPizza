package com.revature.controller;

import com.revature.dto.DeleteAccountInfo;
import com.revature.dto.EditProfile;
import com.revature.dto.RegisterInfo;
import com.revature.exception.AccountUnsuccessfullyEditedException;
import com.revature.exception.AccountUnsuccessfullyRemovedException;
import com.revature.exception.RegisterException;
import com.revature.exception.UserNotFoundException;
import com.revature.model.Employer;
import com.revature.service.EmployerService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserController {

    public static void getUsers(Context context) {
        try {
            List<Employer> allEmployers = EmployerService.getAllEmployers();
            context.json(allEmployers);
        }
        catch (SQLException | IOException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void postUsers(Context context) {
        RegisterInfo accountToRegister = context.bodyAsClass(RegisterInfo.class);

        if (accountToRegister.getAccountType().length() == 0) {
            context.result("You must select an account type");
            context.status(400);
        }
        else if (accountToRegister.getAccountName().length() == 0) {
                context.result("You must enter your full name");
                context.status(400);
        }
        else if (accountToRegister.getUsername().length() == 0) {
            context.result("You must enter a username");
            context.status(400);
        }
        else if (accountToRegister.getPassword().length() == 0) {
            context.result("You must enter a password");
            context.status(400);
        }
        else if (accountToRegister.getEmail().length() == 0) {
            context.result("You must enter an email");
            context.status(400);
        }
        else if (accountToRegister.getPhoneNumber().length() == 0) {
            context.result("You must enter a phone number");
            context.status(400);
        }
        else if (accountToRegister.getLocation().length() == 0) {
            context.result("You must enter a location");
            context.status(400);
        }
        else {
            try {
                EmployerService.registerEmployer(accountToRegister);
                context.result("Successfully registered");
                context.status(201);
            }
            catch (RegisterException e) {
                context.result(e.getMessage());
                context.status(400);
            }
            catch (SQLException e) {
                context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public static void getUser(Context context) {

        String username = context.pathParam("username");

        try {
            Employer employer = EmployerService.getEmployerByUsername(username);
            context.json(employer);
        }
        catch (UserNotFoundException e) {
            context.result(e.getMessage());
            context.status(404);
        }
        catch (SQLException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void putUser(Context context) {
        String username = context.pathParam("username");
        EditProfile profileToEdit = context.bodyAsClass(EditProfile.class);
        try {
            EmployerService.editEmployer(profileToEdit);
            context.result("Profile successfully updated");
            context.status(200);

        } catch (IllegalArgumentException | AccountUnsuccessfullyEditedException e) {
            context.result(e.getMessage());
            context.status(400);
        } catch (SQLException | IOException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void deleteUser(Context context) {
        DeleteAccountInfo accountToRemove = context.bodyAsClass(DeleteAccountInfo.class);

        if (accountToRemove.getEmail() == null || accountToRemove.getPassword() == null) {
            context.result("Email and Password are required");
            context.status(400);
        }
        else {
            try {
                EmployerService.removeEmployerUsingCredentials(accountToRemove.getEmail(), accountToRemove.getPassword());
                context.result("Profile successfully removed");
                context.status(200);

            } catch (AccountUnsuccessfullyRemovedException e) {
                context.status(400);
                context.result(e.getMessage());
            } catch (SQLException | IOException e) {
                context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
