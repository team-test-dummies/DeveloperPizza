package com.revature;

import com.revature.controller.EmployerController;
import com.revature.controller.AuthController;
import com.revature.controller.OrderController;

import com.revature.controller.StartOrderController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
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
        });

        // set login and logout endpoints
        app.post("/login", AuthController::login);
        app.post("/logout", AuthController::logout);
        app.get("/start-order/", StartOrderController::startOrder);


        EmployerController employerController = new EmployerController();
        employerController.mapEndpoint(app);


        // TEST ORDER ENDPOINTS
        OrderController orderController = new OrderController();
        orderController.mapEndpoint(app);

        return app;
    }
}
