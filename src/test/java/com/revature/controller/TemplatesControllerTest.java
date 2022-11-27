package com.revature.controller;

import com.revature.App;
import com.revature.PrototypingApp;
import io.javalin.Javalin;
import io.javalin.testtools.HttpClient;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Map;
public class TemplatesControllerTest {
    private static Javalin app;
    @BeforeMethod
    public void setup() throws SQLException {
        PrototypingApp.setup();
        app = App.initialize();
    }

    @AfterMethod
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
        app = null;
    }

    private static String cookie(HttpClient client, String username, String password) {
        Response response = client.post(
                "/login",
                Map.of(
                        "username", username,
                        "password", password
                )
        );
        return response.header("Set-Cookie");
    }
    @Test void getTemplatesPositive() {
        // Should return 200
        JavalinTest.test(app, (server, client) ->{
            String cookie = cookie(client,"rickmonald","guest");
            Response response = client.request(
                    "/templates",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );
            int actualStatusCode = response.code();
            Assert.assertEquals(actualStatusCode, 200);
        });
    }
    @Test void getLanguagesPositive() {
        // Should return 200
        JavalinTest.test(app, (server, client) ->{
            String cookie = cookie(client,"rickmonald","guest");
            Response response = client.request(
                    "/languages",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );
            int actualStatusCode = response.code();
            Assert.assertEquals(actualStatusCode, 200);
        });
    }
    @Test void getToolsPositive() {

        JavalinTest.test(app, (server, client) ->{
            String cookie = cookie(client,"rickmonald","guest");
            Response response = client.request(
                    "/tools",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );
            int actualStatusCode = response.code();
            Assert.assertEquals(actualStatusCode, 200);
        });
    }





}
