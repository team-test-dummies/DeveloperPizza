package com.revature.controller;

import com.revature.models.Developer;
import com.revature.service.DeveloperService;
import io.javalin.Javalin;

import java.util.List;

public class DeveloperController implements Controller {
    private DeveloperService developerService = new DeveloperService();

    @Override
    public void mapEndpoint(Javalin app) {
        app.get("/developers", ctx -> {
            List<Developer> allDevelopers =  developerService.getAllDevelopers();
            ctx.json(allDevelopers);
        });
    }
}
