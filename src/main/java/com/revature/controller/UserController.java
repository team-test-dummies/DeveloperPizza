package com.revature.controller;

import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.Javalin;

import java.util.List;

public class UserController implements Controller {
    private UserService userService = new UserService();

    @Override
    public void mapEndpoint(Javalin app) {
        app.get("/users", ctx -> {
            List<User> allUsers =  userService.getAllUsers();
            ctx.json(allUsers);
        });
    }
}
