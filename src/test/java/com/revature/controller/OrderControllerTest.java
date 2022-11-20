package com.revature.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.App;
import com.revature.PrototypingApp;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.testtools.HttpClient;
import io.javalin.testtools.JavalinTest;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderControllerTest {

    private static Javalin app;
    private static ObjectMapper mapper = new ObjectMapper();

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
    public void postOrderSingletonPositive() throws JsonProcessingException {

        Map<String, Object> jsonObject = Map.of(
                "name", "science manager",
                "educationRequirement", "BACHELORS",
                "salary", 4000,
                "closed", false,
                "languages", List.of("Java", "CSS"),
                "tools", List.of("Visual Studio Code", "IntelliJ", "Selenium"),
                "userId", 8
        );
        String json = mapper.writeValueAsString(jsonObject);
        RequestBody requestBody = RequestBody.create(json, MediaType.get("application/json"));

        JavalinTest.test(app, (server, client) -> {
            String cookie = cookie(client, "rickmonald", "guest");
            Response response = client.request(
                    "/orders",
                    builder -> {
                        builder
                        .addHeader("Cookie", cookie)
                        .post(requestBody);
                    }
            );

            Assert.assertEquals(response.body().string(), "");
            Assert.assertEquals(response.code(), HttpStatus.CREATED.getCode());
            Assert.assertEquals(response.header("Location"),"/orders/6");

            response = client.request(
                    response.header("Location"),
                    builder -> {
                        builder
                        .addHeader("Cookie", cookie)
                        .get();
                    }
            );

            Assert.assertEquals(response.body().string(),"{\"id\":6,\"name\":\"science manager\",\"educationRequirement\":\"BACHELORS\",\"salary\":4000,\"closed\":false,\"languages\":[\"Java\",\"CSS\"],\"tools\":[\"IntelliJ\",\"Visual Studio Code\",\"Selenium\"],\"userId\":8}");
        });

    }

}
