package com.revature.controller;

import com.revature.App;
import com.revature.PrototypingApp;
import com.revature.data.records.Authority;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

import static com.revature.data.enums.Role.CUSTOMER;

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
    public void postUserPositive() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "jane");
            requestJson.put("password", "password");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            Response response = client.post("/users", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,201);
            Assert.assertEquals(responseBody, "{\"message\":\"Successfully registered\"}");
        });

    }

    @Test
    public void postUserNegative() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "");
            requestJson.put("password", "password");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            Response response = client.post("/users", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"You must enter a username\"}");
        });

    }
    @Test
    public void postUserNoAccount() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "jane_doe");
            requestJson.put("password", "password");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            Response response = client.post("/users", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"You must select an account type\"}");
        });
    }

    @Test
    public void postUserNoAccountName() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "");
            requestJson.put("username", "jane_doe");
            requestJson.put("password", "password");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            Response response = client.post("/users", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"You must enter your full name\"}");
        });
    }
    @Test
    public void postUserNoPassword() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "jane_doe");
            requestJson.put("password", "");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            Response response = client.post("/users", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"You must enter a password\"}");
        });
    }
    @Test
    public void postUserNoPhone() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "jane_doe");
            requestJson.put("password", "password");
            requestJson.put("phoneNumber", "");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            Response response = client.post("/users", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"You must enter a phone number\"}");
        });
    }
    @Test
    public void postUserEmail() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "jane_doe");
            requestJson.put("password", "password");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "");
            requestJson.put("location", "Georgia");

            Response response = client.post("/users", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"You must enter an email\"}");
        });
    }

    @Test
    public void postUserNoLocation() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "jane_doe");
            requestJson.put("password", "password");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "");

            Response response = client.post("/users", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"You must enter a location\"}");
        });

    }



    @Test // NEED TO FIGURE OUT HOW TO INCLUDE AUTHORIZATION TO VIEW USER INFO
    public void getUserPositive() {
        throw new SkipException("unimplemented");

        /* JavalinTest.test(app, (server, client) -> {
            Response response = client.get("/user");
                        int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,200);
            Assert.assertEquals(responseBody, "{\"id\":1," +
                    "\"accountType\":\"CUSTOMER\"," +
                    "\"accountName\":\"madison_kora\"," +
                    "\"username\":\"madkor436\"," +
                    "\"password\":\"k�5�O���\\u0015D�a=�z��kl\\\\q�I���\\u000F�x��\"," +
                    "\"phoneNumber\":\"505-684-9399\"," +
                    "\"email\":\"madkor436@company.net\"," +
                    "\"location\":\"New Mexico\"}"
            );
        }); */
    }

    @Test
    public void editUserPositive() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountName", "madison_kora");
            requestJson.put("username", "madkor436");
            requestJson.put("password", "k�5�O���\u0015D�a=�z��kl\\\\q�I���\u000F�x��");
            requestJson.put("phoneNumber", "505-684-9399");
            requestJson.put("email", "madkor436@company.net");
            requestJson.put("location", "New Mexico");

            Response response = client.put("/users/madkor436", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,200);
            Assert.assertEquals(responseBody, "{\"message\":\"Profile successfully updated\"}");
        });
    }

    @Test
    public void editUserNegative() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountName", "madison_kora");
            requestJson.put("username", "invalidUsername");
            requestJson.put("password", "k�5�O���\u0015D�a=�z��kl\\\\q�I���\u000F�x��");
            requestJson.put("phoneNumber", "505-684-9399");
            requestJson.put("email", "madkor436@company.net");
            requestJson.put("location", "New Mexico");

            Response response = client.put("/users/madkor436", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Profile was not edited\"}");
        });
    }

    @Test
    public void removeUserPositive() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("email", "madkor436@company.net");
            requestJson.put("password", "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��");

            Response response = client.delete("/users/madkor436", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,200);
            Assert.assertEquals(responseBody, "{\"message\":\"Profile successfully removed\"}");
        });
    }

    @Test
    public void removeUserNegative() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("email", "madkor436@company.net");
            requestJson.put("password", "invalidPassword");

            Response response = client.delete("/users/madkor436", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Profile was not removed\"}");
        });
    }


}

