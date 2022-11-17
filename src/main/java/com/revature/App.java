package com.revature;

import com.revature.controller.*;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

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

        // ENDPOINTS WITH METHOD REFERENCES
        app.post("/login", AuthController::login);
        app.post("/logout", AuthController::logout);

        app.get("/user/", UserController::getUser);
        app.get("/users", UserController::getUsers);
        app.post("/users", UserController::postUsers);
        app.put("/users/{username}", UserController::putUser);
        app.delete("/users/{username}", UserController::deleteUser);

        app.get("/orders", OrderController::getOrders); // returns the orders of the logged-in user
        app.post("/orders", OrderController::postOrders);
        app.get("/orders/{order-id}", OrderController::getOrder);
        app.put("/orders/{order-id}", OrderController::putOrder);
        app.delete("/orders/{order-id}", OrderController::deleteOrder);

        app.get("/templates", TemplatesController::getTemplates);
        app.get("/languages", TemplatesController::getLanguages);
        app.get("/tools", TemplatesController::getTools);
        app.get("/soft-skills", TemplatesController::getSoftSkills);
        app.get("/services", TemplatesController::getServices);
        /* error handling should happen here using app.exception and not in the static functions */

        app.get("/start-order/", StartOrderController::startOrder);

        return app;
    }
}
