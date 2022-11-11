package com.revature.controller;

import com.revature.dto.LoginInfo;
import com.revature.dto.Message;
import io.javalin.Javalin;
import jakarta.servlet.http.HttpSession;

public class AuthController implements Controller {

    @Override
    public void mapEndpoint(Javalin app) {
        app.post("/login", (ctx) -> {
            LoginInfo login = new LoginInfo();

            if (login.getUsername() == null || login.getPassword() == null) {
                ctx.json(new Message("username and/or password was not provided!"));
                ctx.status(400);
            } else {
                try {
                    User user = userService.login(login.getUsername(), login.getPassword());

                    // Create an HttpSession and associate the user object with that session
                    // HttpSessions are used to track which client is sending a particular request
                    HttpSession httpSession = ctx.req().getSession();
                    httpSession.setAttribute("user_info", user);
                    ctx.json(user);
                } catch (LoginException e) {
                    ctx.req().getSession().invalidate();
                    ctx.status(400);
                    ctx.json(new Message(e.getMessage()));
                }
            }
        });
    }
}
