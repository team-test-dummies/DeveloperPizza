package com.revature.controller;

import com.revature.model.Employer;
import com.revature.service.EmployerService;
import io.javalin.Javalin;

import java.util.List;

public class EmployerController implements Controller {
    private EmployerService employerService = new EmployerService();

@Deprecated
public class EmployerController {

    public void mapEndpoint(Javalin app) {
        app.get("/employers", ctx -> {
            List<Employer> allEmployers =  employerService.getAllEmployers();
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

        app.get("employers/{username}/profile", ctx -> {
            String username = ctx.pathParam("username");

            try {
                Employer employer = EmployerService.getEmployerByUsername(username);
                ctx.json(employer);

            } catch (UserNotFoundException e) {
                ctx.result(e.getMessage());
                ctx.status(404);
            }
        });

    }
}
