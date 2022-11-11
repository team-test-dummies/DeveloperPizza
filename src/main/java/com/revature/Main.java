package com.revature;

import com.revature.controller.AuthController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.bundled.CorsPluginConfig;

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
        AuthController authController = new AuthController();
        authController.mapEndpoint(app);
        // start the server
        app.start(8080);
    }
}
