package com.revature.controllers;

import com.revature.models.Developer;
import com.revature.services.DeveloperService;
import io.javalin.Javalin;

import java.util.List;

public class DeveloperController implements Controller{
    private DeveloperService developerService = new DeveloperService();

    @Override
    public void mapEndpoint(Javalin app) {
        app.get("/developers", ctx -> {
            List<Developer> allDevelopers =  developerService.getAllDevelopers();
            ctx.json(allDevelopers);
        });
    }
}
