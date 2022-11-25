package com.revature.controller;

import com.revature.data.records.*;
import com.revature.data.exception.AccountUnsuccessfullyEditedException;
import com.revature.data.exception.AccountUnsuccessfullyRemovedException;
import com.revature.data.exception.RegisterException;
import com.revature.data.exception.UserNotFoundException;
import com.revature.service.UserService;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.openapi.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UserController {

    @OpenApi(
            summary = "Get all users available to user",
            operationId = "getUsers",
            path = "/users",
            methods = HttpMethod.GET,
            tags = {"User"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Customer[].class)}),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void getUsers(Context context) {
        try {
            List<Customer> allCustomers = UserService.getAllCustomers();
            context.json(allCustomers);
        }
        catch (SQLException | IOException e) {
            context.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @OpenApi(
            summary = "Create a new customer in the database",
            operationId = "postUsers",
            path = "/users",
            methods = HttpMethod.POST,
            tags = {"User"},
            requestBody = @OpenApiRequestBody(content = @OpenApiContent(from = Customer.class)),
            responses = {
                    @OpenApiResponse(status = "201"),
                    @OpenApiResponse(status = "400"),
                    @OpenApiResponse(status = "500")
            }
    )
    public static void postUsers(Context context) {
        RegisterInfo accountToRegister = context.bodyAsClass(RegisterInfo.class);

        if (accountToRegister.getAccountType().length() == 0) {
            context.json(new Message("You must select an account type"));
            context.status(400);
        }
        else if (accountToRegister.getAccountName().length() == 0) {
                context.json(new Message("You must enter your full name"));
                context.status(400);
        }
        else if (accountToRegister.getUsername().length() == 0) {
            context.json(new Message("You must enter a username"));
            context.status(400);
        }
        else if (accountToRegister.getPassword().length() == 0) {
            context.json(new Message("You must enter a password"));
            context.status(400);
        }
        else if (accountToRegister.getPhoneNumber().length() == 0) {
            context.json(new Message("You must enter a phone number"));
            context.status(400);
        }
        else if (accountToRegister.getEmail().length() == 0) {
            context.json(new Message("You must enter an email"));
            context.status(400);
        }
        else if (accountToRegister.getLocation().length() == 0) {
            context.json(new Message("You must enter a location"));
            context.status(400);
        }
        else {
            try {
                UserService.registerCustomer(accountToRegister);
                context.json(new Message("Successfully registered"));
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

    @OpenApi(
            summary = "Get specific user",
            operationId = "getUser",
            path = "/user/",
            methods = HttpMethod.GET,
            tags = {"User"},
            responses = {
                    @OpenApiResponse(status = "200", content = {@OpenApiContent(from = Customer.class)}),
                    @OpenApiResponse(status = "401"),
                    @OpenApiResponse(status = "404"),
                    @OpenApiResponse(status = "500")
            }
    )
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

    @OpenApi(
            summary = "Update existing user",
            operationId = "putUser",
            path = "/users/{user-id}",
            methods = HttpMethod.PUT,
            tags = {"User"},
            requestBody = @OpenApiRequestBody(content = @OpenApiContent(from = Customer.class)),
            responses = {
                    @OpenApiResponse(status = "200"),
                    @OpenApiResponse(status = "400"),
                    @OpenApiResponse(status = "500")
            }
    )
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

    @OpenApi(
            summary = "Delete existing user",
            operationId = "deleteUser",
            path = "/users/{user-id}",
            methods = HttpMethod.DELETE,
            tags = {"User"},
            responses = {
                    @OpenApiResponse(status = "200"),
                    @OpenApiResponse(status = "400"),
                    @OpenApiResponse(status = "500")
            }
    )
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
