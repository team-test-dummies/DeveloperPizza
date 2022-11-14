package com.revature.controller;

import com.revature.model.Employer;
import com.revature.service.EmployerService;
import io.javalin.Javalin;

import java.util.List;

public class EmployerController implements Controller {
    private EmployerService employerService = new EmployerService();

    @Deprecated
    public void mapEndpoint(Javalin app) {
        app.get("/employers", ctx -> {
            List<Employer> allEmployers =  employerService.getAllEmployers();
            ctx.json(allEmployers);
        });


    }
}
