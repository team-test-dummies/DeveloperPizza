package com.revature.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.App;
import com.revature.PrototypingApp;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.testtools.HttpClient;
import io.javalin.testtools.JavalinTest;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.Iterator;
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

    @DataProvider(name = "endpoints and methods")
    public Iterator<Object[]> endpointsAndMethods() {
        return List.of(
                new Object[] {"/orders", "POST"},
                new Object[] {"/orders", "GET"},
                new Object[] {"/orders/1", "GET"},
                new Object[] {"/orders/1", "PUT"},
                new Object[] {"/orders/1", "DELETE"}
        ).iterator();
    }

    @Test(dataProvider = "endpoints and methods")
    public void unauthorizedTest(String endpoint, String method) {
        RequestBody requestBody = RequestBody.create("", MediaType.get("application/json"));
        JavalinTest.test(app, (server, client) -> {
            Response response = client.request(
                    endpoint,
                    builder -> {
                        switch (method) {
                            case "POST" -> builder.post(requestBody);
                            case "PUT" -> builder.put(requestBody);
                            case "GET" -> builder.get();
                            case "DELETE" -> builder.delete();
                        }
                    }
            );

            Assert.assertEquals(response.code(), HttpStatus.UNAUTHORIZED.getCode());
        });
    }

    @DataProvider(name = "singleton endpoints and methods")
    public Iterator<Object[]> singletonEndpointsAndMethods() {
        return List.of(
                new Object[] {"/orders/1", "GET"},
                new Object[] {"/orders/1", "PUT"},
                new Object[] {"/orders/1", "DELETE"}
        ).iterator();
    }

    @Test(dataProvider = "singleton endpoints and methods")
    public void forbiddenTest(String endpoint, String method) throws JsonProcessingException {
        Map<String, Object> jsonObject = Map.of(
                "id", 1,
                "name", "science manager",
                "educationRequirement", "BACHELORS",
                "salary", 4000,
                "closed", false,
                "languages", List.of("Java", "CSS"),
                "tools", List.of("Visual Studio Code", "IntelliJ", "Selenium"),
                "userId", 9
        );
        String json = mapper.writeValueAsString(jsonObject);
        RequestBody requestBody = RequestBody.create(json, MediaType.get("application/json"));

        JavalinTest.test(app, (server, client) -> {
            String cookie = cookie(client, "halffoods", "guest");
            Response response = client.request(
                    endpoint,
                    builder -> {
                        switch (method) {
                            case "PUT" -> {
                                builder
                                .addHeader("Cookie", cookie)
                                .put(requestBody);
                            }
                            case "GET" -> {
                                builder
                                .addHeader("Cookie", cookie)
                                .get();
                            }
                            case "DELETE" -> {
                                builder
                                .addHeader("Cookie", cookie)
                                .delete();
                            }
                        }
                    }
            );

            Assert.assertEquals(response.code(), HttpStatus.FORBIDDEN.getCode());
        });
    }


    @DataProvider(name = "valid for order 1")
    public Iterator<Object[]> validForOrderOne() {
        return List.of(
                new Object[] {"rickmonald", "guest"},
                new Object[] {"staff", "guest"},
                new Object[] {"admin", "guest"}
        ).iterator();
    }

    @Test(dataProvider = "valid for order 1")
    public void getOrderPositive(String username, String password) {

        JavalinTest.test(app, (server, client) -> {
            String cookie = cookie(client, username, password);
            Response response = client.request(
                "/orders/1",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );

            Assert.assertEquals(response.body().string(),"{\"id\":1,\"name\":\"science person\",\"educationRequirement\":\"BACHELORS\",\"salary\":40000,\"closed\":false,\"languages\":[\"Java\",\"CSS\",\"HTML\"],\"tools\":[\"IntelliJ\",\"Visual Studio Code\",\"Selenium\"],\"userId\":8}");
        });

    }

    @Test
    public void getOrderForbiddenTest() {
        JavalinTest.test(app, (server, client) -> {
            String cookie = cookie(client, "halffoods", "guest");

            Response response = client.request(
                    "/orders/1",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );

            Assert.assertEquals(response.code(), HttpStatus.FORBIDDEN.getCode());

        });
    }

    @Test
    public void getOrderNotFoundTest() {
        JavalinTest.test(app, (server, client) -> {
            String cookie = cookie(client, "halffoods", "guest");

            Response response = client.request(
                    "/orders/1000",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );

            Assert.assertEquals(response.code(), HttpStatus.NOT_FOUND.getCode());

        });
    }

    @Test
    public void getOrdersPositive() throws JsonProcessingException {

        JavalinTest.test(app, (server, client) -> {
            String cookie = cookie(client, "rickmonald", "guest");

            Response response = client.request(
                    "/orders",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );
            Assert.assertEquals(response.body().string(),"[{\"id\":1,\"name\":\"science person\",\"educationRequirement\":\"BACHELORS\",\"salary\":40000,\"closed\":false,\"languages\":[\"Java\",\"CSS\",\"HTML\"],\"tools\":[\"IntelliJ\",\"Visual Studio Code\",\"Selenium\"],\"userId\":8},{\"id\":2,\"name\":\"computer science person\",\"educationRequirement\":\"ASSOCIATES\",\"salary\":50000,\"closed\":false,\"languages\":[\"Javascript\",\"HTML\"],\"tools\":[\"Javalin\",\"Selenium\"],\"userId\":8},{\"id\":3,\"name\":\"person\",\"educationRequirement\":\"ASSOCIATES\",\"salary\":60000,\"closed\":false,\"languages\":[\"Javascript\",\"HTML\"],\"tools\":[\"Javalin\",\"Selenium\"],\"userId\":8}]");
        });

    }

    @Test(dependsOnMethods = "getOrderPositive")
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

    @Test(dependsOnMethods = "getOrderPositive")
    public void postOrdersPositive() throws JsonProcessingException {

        Map<String, Object> jsonObject = Map.of(
                "name", "science manager",
                "educationRequirement", "BACHELORS",
                "salary", 4000,
                "closed", false,
                "languages", List.of("Java", "CSS"),
                "tools", List.of("Visual Studio Code", "IntelliJ", "Selenium"),
                "userId", 8
        );
        String json = mapper.writeValueAsString(new Object[] {jsonObject, jsonObject});
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
            Assert.assertEquals(response.header("Location"),"/orders");

            response = client.request(
                    "/orders/6",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );

            Assert.assertEquals(response.body().string(),"{\"id\":6,\"name\":\"science manager\",\"educationRequirement\":\"BACHELORS\",\"salary\":4000,\"closed\":false,\"languages\":[\"Java\",\"CSS\"],\"tools\":[\"IntelliJ\",\"Visual Studio Code\",\"Selenium\"],\"userId\":8}");

            response = client.request(
                    "/orders/7",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );

            Assert.assertEquals(response.body().string(),"{\"id\":7,\"name\":\"science manager\",\"educationRequirement\":\"BACHELORS\",\"salary\":4000,\"closed\":false,\"languages\":[\"Java\",\"CSS\"],\"tools\":[\"IntelliJ\",\"Visual Studio Code\",\"Selenium\"],\"userId\":8}");

        });

    }

    @Test(dependsOnMethods = "getOrderPositive")
    public void putOrderPositive() throws JsonProcessingException {

        Map<String, Object> jsonObject = Map.of(
                "id", 1,
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
                    "/orders/1",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .put(requestBody);
                    }
            );

            Assert.assertEquals(response.body().string(), "");
            Assert.assertEquals(response.code(), HttpStatus.NO_CONTENT.getCode());

            response = client.request(
                "/orders/1",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );

            Assert.assertEquals(response.body().string(),"{\"id\":1,\"name\":\"science manager\",\"educationRequirement\":\"BACHELORS\",\"salary\":4000,\"closed\":false,\"languages\":[\"Java\",\"CSS\"],\"tools\":[\"IntelliJ\",\"Visual Studio Code\",\"Selenium\"],\"userId\":8}");
        });

    }

    // getOrderPositive ensures the test data exists before test start
    @Test(dependsOnMethods = {"getOrderPositive", "getOrderNotFoundTest"} )
    public void deleteOrderPositive() throws JsonProcessingException {

        JavalinTest.test(app, (server, client) -> {

            String cookie = cookie(client, "rickmonald", "guest");
            Response response = client.request(
                    "/orders/1",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .delete();
                    }
            );

            Assert.assertEquals(response.body().string(), "");
            Assert.assertEquals(response.code(), HttpStatus.NO_CONTENT.getCode());

            response = client.request(
                    "/orders/1",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .get();
                    }
            );

            Assert.assertEquals(response.code(),HttpStatus.NOT_FOUND.getCode());
        });

    }

    @Test
    public void deleteNonExistentOrder() throws JsonProcessingException {

        JavalinTest.test(app, (server, client) -> {

            String cookie = cookie(client, "rickmonald", "guest");
            Response response = client.request(
                    "/orders/1000",
                    builder -> {
                        builder
                                .addHeader("Cookie", cookie)
                                .delete();
                    }
            );

            Assert.assertEquals(response.body().string(), "");
            Assert.assertEquals(response.code(), HttpStatus.NO_CONTENT.getCode());
        });

    }




}
