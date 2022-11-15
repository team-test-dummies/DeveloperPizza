package com.revature;

import com.revature.controller.*;

import com.revature.model.Order;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.security.RouteRole;

public class App {

    public static void main(String[] args) {
        // start the server on run
        try {
            initialize().start();
        } catch (Exception ignored) {}
    }

    public static Javalin initialize() {
        // add static files 'controller'
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add(staticConfig -> {
                staticConfig.hostedPath = "/";
                staticConfig.directory = "/public";
                staticConfig.location = Location.CLASSPATH;
            });
            config.plugins.enableCors((cors) -> {
                cors.add(it -> {
                    it.defaultScheme = "http";
                    it.allowHost("http://127.0.0.1:5500");
                    it.allowHost("http://localhost:8080");
                });
            });
        });

        // set login and logout endpoints
        app.post("/login", AuthController::login);
        app.post("/logout", AuthController::logout);

        app.get("/users", UserController::getUsers);
        app.post("/users", UserController::postUsers);
        app.get("/users/{username}", UserController::getUser);
        app.put("/users/{username}", UserController::putUser);
        app.delete("/users/{username}", UserController::deleteUser);

        app.get("/orders", OrderController::getOrders);
        app.post("/orders", OrderController::postOrders);
        app.get("/orders/{order-id}", OrderController::getOrder);
        app.put("/orders/{order-id}", OrderController::putOrder);
        app.delete("/orders/{order-id}", OrderController::deleteOrder);

        app.get("/templates", TemplatesController::getTemplates);
        app.get("/languages", TemplatesController::getLanguages);
        app.get("/tools", TemplatesController::getTools);
        app.get("/soft-skills", TemplatesController::getSoftSkills);
        app.get("/services", TemplatesController::getServices);


        app.get("/start-order/", StartOrderController::startOrder);

        return app;
    }
}
