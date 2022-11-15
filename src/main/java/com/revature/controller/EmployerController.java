package com.revature.controller;

import com.revature.dto.DeleteAccountInfo;
import com.revature.dto.EditProfile;
import com.revature.dao.EmployerDao;
import com.revature.dto.RegisterInfo;
import com.revature.exception.AccountUnsuccessfullyEditedException;
import com.revature.exception.AccountUnsuccessfullyRemovedException;
import com.revature.exception.RegisterException;
import com.revature.exception.UserNotFoundException;
import com.revature.model.Employer;
import com.revature.service.EmployerService;
import io.javalin.Javalin;

import java.util.List;

public class EmployerController {
    public void mapEndpoint(Javalin app) {
        app.get("/users", ctx -> {
            List<Employer> allEmployers =  EmployerService.getAllEmployers();
            ctx.json(allEmployers);
        });

        app.post("/register", ctx -> {
            RegisterInfo accountToRegister = ctx.bodyAsClass(RegisterInfo.class);

            if (accountToRegister.getAccountType().length() == 0) {
                ctx.result("You must select an account type");
                ctx.status(400);
            }
            else if (accountToRegister.getAccountName().length() == 0) {
                ctx.result("You must enter your full name");
                ctx.status(400);
            }
            else if (accountToRegister.getUsername().length() == 0) {
                ctx.result("You must enter a username");
                ctx.status(400);
            }
            else if (accountToRegister.getPassword().length() == 0) {
                ctx.result("You must enter a password");
                ctx.status(400);
            }
            else if (accountToRegister.getEmail().length() == 0) {
                ctx.result("You must enter an email");
                ctx.status(400);
            }
            else if (accountToRegister.getPhoneNumber().length() == 0) {
                ctx.result("You must enter a phone number");
                ctx.status(400);
            }
            else if (accountToRegister.getLocation().length() == 0) {
                ctx.result("You must enter a location");
                ctx.status(400);
            } else {
                try {
                    EmployerService.registerEmployer(accountToRegister);
                    ctx.result("Successfully registered");
                    ctx.status(201);

                } catch (RegisterException e) {
                    ctx.result(e.getMessage());
                    ctx.status(400);
                }
            }
        });

        app.get("users/{username}/profile", ctx -> {
            String username = ctx.pathParam("username");

            try {
                Employer employer = EmployerService.getEmployerByUsername(username);
                ctx.json(employer);

            } catch (UserNotFoundException e) {
                ctx.result(e.getMessage());
                ctx.status(404);
            }
        });

        app.patch("users/{username}/edit", ctx -> {
            String username = ctx.pathParam("username");
            EditProfile profileToEdit = ctx.bodyAsClass(EditProfile.class);

            try {
                EmployerService.editEmployer(profileToEdit);
                ctx.result("Profile successfully updated");
                ctx.status(200);

            } catch (IllegalArgumentException | AccountUnsuccessfullyEditedException e) {
                ctx.result(e.getMessage());
                ctx.status(400);
            }
        });

        app.delete("users/{username}/delete", ctx -> {
            DeleteAccountInfo accountToRemove = ctx.bodyAsClass(DeleteAccountInfo.class);

            if (accountToRemove.getEmail() == null || accountToRemove.getPassword() == null) {
                ctx.result("Email and Password are required");
                ctx.status(400);
            } else {
                try {
                    EmployerService.removeEmployerUsingCredentials(accountToRemove.getEmail(), accountToRemove.getPassword());
                    ctx.result("Profile successfully removed");
                    ctx.status(200);

                } catch (AccountUnsuccessfullyRemovedException e) {
                    ctx.status(400);
                    ctx.result(e.getMessage());
                }
            }
        });
    }
}
