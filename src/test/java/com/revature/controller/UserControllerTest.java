package com.revature.controller;

import com.revature.App;
import com.revature.PrototypingApp;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class UserControllerTest {

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


    @Test
    public void getUserPositive() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("username", "madkor436");

            Response response = client.get("/users{username}", (Consumer<Request.Builder>) requestJson);
            int actualStatusCode = response.code();
            String responseBody = response.body().string();

            Assert.assertEquals(actualStatusCode,200);
            Assert.assertEquals(responseBody, "{\"id\":1, \"accoutType\":\"CUSTOMER\", \"accountName\":\"madison_kora\"," +
                    "\"username\":\"madkor436\", \"password\":\"k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��\"," +
                    "\"phoneNumber\":\"505-684-9399\", \"email\":\"madkor436@company.net\", \"location\":\"New Mexico\"}");
        });
    }

    @Test
    public void postUserPositive() {

    }
}

