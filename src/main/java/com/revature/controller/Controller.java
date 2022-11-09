package com.revature.controllers;

import io.javalin.Javalin;

public interface Controller {
    void mapEndpoint(Javalin app);
}
