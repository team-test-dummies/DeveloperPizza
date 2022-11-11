package com.revature;

import com.revature.controller.OrderController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class Main {
    public static void main(String[] args) {

        // add static files 'controller'
        Javalin app = Javalin.create(config -> {
            config.staticFiles.add(staticConfig -> {
                staticConfig.hostedPath = "/";
                staticConfig.directory = "/public";
                staticConfig.location = Location.CLASSPATH;
            });
        });

        // TEST ORDER ENDPOINTS
        OrderController orderController = new OrderController();
        orderController.mapEndpoint(app);

        // start the server
        app.start(8080);
    }
}
