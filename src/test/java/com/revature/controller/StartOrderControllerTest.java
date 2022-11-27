package com.revature.controller;

import com.revature.App;
import com.revature.PrototypingApp;
import io.javalin.Javalin;
import io.javalin.testtools.HttpClient;
import io.javalin.testtools.JavalinTest;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.SQLException;
import java.util.Map;

public class StartOrderControllerTest {
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
    @Test
    public void startOrderTestAuthNegative() {
        // NO one logged in should return 401

        JavalinTest.test(app, (server, client) ->{
            Response response = client.get("/start-order");
            int actualStatusCode = response.code();
            Assert.assertEquals(actualStatusCode, 401);
        });
    }

    @Test void startOrderPositive() {
        // Should return 200 if logged in
        JavalinTest.test(app, (server, client) ->{
            String cookie = cookie(client,"rickmonald","guest");
            Response response = client.request(
                    "/start-order",
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

