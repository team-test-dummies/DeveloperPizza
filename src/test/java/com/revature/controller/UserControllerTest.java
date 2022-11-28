package com.revature.controller;

import com.revature.App;
import com.revature.PrototypingApp;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.Request;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
            requestJson.put("username", "janedoe");
            requestJson.put("password", "passworD5");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/users", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,201);
            Assert.assertEquals(responseBody, "Successfully registered");
        });

    }

    @Test
    public void postUserNegative() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "");
            requestJson.put("password", "passworD5");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/users", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode, 400);
            Assert.assertEquals(responseBody, "{\"message\":\"Username should be 6-16 characters long\"}");
        });
    }
    @Test
    public void postUserNegativeNoContraints() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "jane_doe");
            requestJson.put("password", "passworD5");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/users", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Username cannot include special characters (@, $, !, *, etc)\"}");
        });

    }
    @Test
    public void postUserNoAccount() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "janedoe");
            requestJson.put("password", "passworD5");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");
            Response response = client.post("/users", requestJson);

            int actualStatusCode = response.code();
            String responseBody = Objects.requireNonNull(response.body()).string();

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Account type is required\"}");
        });
    }

    @Test
    public void postUserNoAccountName() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "");
            requestJson.put("username", "janedoe");
            requestJson.put("password", "passworD5");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/users", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Enter your full name\"}");
        });
    }
    @Test
    public void postUserNoPassword() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "janedoe");
            requestJson.put("password", "");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/users", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Password should be 6-16 characters long\"}");
        });
    }
    @Test
    public void postUserPasswordNotMeetingContraints() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "janedoe");
            requestJson.put("password", "asdaskk");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/users", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Must include one uppercase letter, one lowercase letter, and one number\"}");
        });
    }
    @Test
    public void postUserNoPhone() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "janedoe");
            requestJson.put("password", "passworD5");
            requestJson.put("phoneNumber", "");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "Georgia");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/users", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Enter a valid phone number\"}");
        });
    }
    @Test
    public void postUserEmail() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "janedoe");
            requestJson.put("password", "passworD5");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "");
            requestJson.put("location", "Georgia");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/users", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Enter a valid email\"}");
        });
    }

    @Test
    public void postUserNoLocation() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("accountType", "CUSTOMER");
            requestJson.put("accountName", "jane_doe");
            requestJson.put("username", "janedoe");
            requestJson.put("password", "passworD5");
            requestJson.put("phoneNumber", "555-555-5555");
            requestJson.put("email", "jane@gmail.com");
            requestJson.put("location", "");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.post("/users", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Enter a location\"}");
        });
    }

    @Test void getAllUsersTest() {
        // Should return 200
        JavalinTest.test(app, (server, client) ->{
            Response response = client.request(
                    "/users",
                    Request.Builder::get
            );
            int actualStatusCode = response.code();
            Assert.assertEquals(actualStatusCode, 200);
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

            int actualStatusCode;
            String responseBody;
            try (Response response = client.put("/users/madkor436", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

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

            int actualStatusCode;
            String responseBody;
            try (Response response = client.put("/users/madkor436", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Profile was not edited\"}");
        });
    }

    @Test
    public void removeUserPositive() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("username", "madkor436");
            requestJson.put("password", "guest");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.delete("/users/madkor436", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,200);
            Assert.assertEquals(responseBody, "Profile successfully removed");
        });
    }

    @Test
    public void removeUserNegative() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("username", "madkor436");
            requestJson.put("password", "invalidPassword");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.delete("/users/madkor436", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Profile was not removed\"}");
        });
    }
    @Test
    public void removeUserEmptyPassNegative() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("username", "madkor436");
            requestJson.put("password", "");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.delete("/users/madkor436", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Password is required\"}");
        });
    }

    @Test
    public void removeUserEmptyUserNegative() {
        JavalinTest.test(app, (server, client) -> {
            Map<String, Object> requestJson = new HashMap<>();
            requestJson.put("username", "");
            requestJson.put("password", "guest");

            int actualStatusCode;
            String responseBody;
            try (Response response = client.delete("/users/madkor436", requestJson)) {

                actualStatusCode = response.code();
                responseBody = Objects.requireNonNull(response.body()).string();
            }

            Assert.assertEquals(actualStatusCode,400);
            Assert.assertEquals(responseBody, "{\"message\":\"Username is required\"}");
        });
    }


}

