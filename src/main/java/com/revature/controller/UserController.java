package com.revature.controller;

import com.revature.data.records.*;
import com.revature.data.exception.AccountUnsuccessfullyEditedException;
import com.revature.data.exception.AccountUnsuccessfullyRemovedException;
import com.revature.data.exception.RegisterException;
import com.revature.data.exception.UserNotFoundException;
import com.revature.service.UserService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class UserController {
    static Pattern solidUsername = Pattern.compile("^\\s*[A-Za-z0-9]+\\s*$");
    static Pattern solidPassword = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,16}$");
    static Pattern solidPhoneNumber = Pattern.compile("^\\d{3}-\\d{3}-\\d{4}$");
    static Pattern solidEmail = Pattern.compile("^[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\\\.[A-Z]{2,6}$");
    public static void getUsers(Context context) {
        try {
            List<Customer> allCustomers = UserService.getAllCustomers();
            context.json(allCustomers);
        }
        catch (SQLException | IOException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public static void postUsers(Context context) {

        RegisterInfo accountToRegister = context.bodyAsClass(RegisterInfo.class);

        if (accountToRegister.getAccountName().length() == 0) {
                context.json(new Message("Enter your full name"));
                context.status(400);
        } else if (accountToRegister.getUsername().length() == 0 || accountToRegister.getUsername().length() < 6 || accountToRegister.getUsername().length() > 16) {
            context.json(new Message("Username should be 6-16 characters long"));
            context.status(400);
        } else if (!solidUsername.matcher(accountToRegister.getUsername()).find()) {
            context.json(new Message("Username cannot include special characters (@, $, !, *, etc)"));
            context.status(400);
        } else if (accountToRegister.getPassword().length() == 0 || accountToRegister.getPassword().length() < 6 || accountToRegister.getPassword().length() > 16) {
            context.json(new Message("Password should be 6-16 characters long"));
            context.status(400);
        } else if (!solidPassword.matcher(accountToRegister.getPassword()).find()) {
            context.json(new Message("Must include one uppercase letter, one lowercase letter, and one number"));
            context.status(400);
        } else if (accountToRegister.getPhoneNumber().length() == 0 || !solidPhoneNumber.matcher(accountToRegister.getPhoneNumber()).find()) {
            context.json(new Message("Enter a valid phone number"));
            context.status(400);
        } else if (accountToRegister.getEmail().length() == 0 || !solidEmail.matcher(accountToRegister.getEmail()).find()) {
            context.json(new Message("Enter a valid email"));
            context.status(400);
        } else if (accountToRegister.getLocation().length() == 0) {
            context.json(new Message("Enter a location"));
            context.status(400);
        } else {
            try {
                UserService.registerCustomer(accountToRegister);
                context.result("Successfully registered");
                context.status(201);
            } catch (RegisterException e) {
                context.result(e.getMessage());
                context.status(400);
            } catch (SQLException e) {
                context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public static void getUser(Context context) {
        Authority authority = (Authority) context.req().getSession().getAttribute("authority");
        if (authority == null) {
            context.status(401);
        } else {
            try {
                Customer customer = UserService.getUserById(authority.id());
                context.json(customer);
            } catch (UserNotFoundException e) {
                context.json(new Message(e.getMessage()));
                context.status(404);
            } catch (SQLException e) {
                context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public static void putUser(Context context) throws SQLException {
        String username = context.pathParam("username");
        UserService.getCustomerByUsername(username);
        EditProfile profileToEdit = context.bodyAsClass(EditProfile.class);

        try {
            UserService.editCustomer(profileToEdit);
            context.json(new Message("Profile successfully updated"));
            context.status(200);

        } catch (AccountUnsuccessfullyEditedException e) {
            context.json(new Message(e.getMessage()));
            context.status(400);
        } catch (SQLException | IOException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    public static void deleteUser(Context context) throws SQLException {
        String username = context.pathParam("username");
        DeleteAccountInfo accountToRemove = context.bodyAsClass(DeleteAccountInfo.class);
        UserService.getCustomerByUsername(username);
        if (accountToRemove.getEmail() == null || accountToRemove.getEmail().length() == 0) {
            context.json(new Message("Email is required"));
            context.status(400);
        }
        else if (accountToRemove.getPassword() == null || accountToRemove.getPassword().length() == 0) {
            context.json(new Message("Password is required"));
            context.status(400);
        }
        else {
            try {
                UserService.removeCustomerUsingCredentials(accountToRemove);
                context.json(new Message("Profile successfully removed"));
                context.status(200);

            } catch (AccountUnsuccessfullyRemovedException e) {
                context.status(400);
                context.json(new Message(e.getMessage()));
            } catch (SQLException | IOException e) {
                context.status(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
