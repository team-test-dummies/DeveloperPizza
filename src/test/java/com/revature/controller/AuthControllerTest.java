package com.revature.controller;

import com.revature.App;
import com.revature.PrototypingApp;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;
import io.javalin.testtools.JavalinTest;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.SQLException;
import java.util.Map;

public class AuthControllerTest {

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
    public static void loginPositive() {
        // Arrange
        String username = "employer";
        String password = "guest";

        // Act
        JavalinTest.test(app, (server, client) -> {
            Response response = client.post(
                    "/login",
                    Map.of(
                            "username", username,
                            "password", password
                    )
            );

            // Assert
            Assert.assertTrue(
                response.header("Set-Cookie").contains("JSESSIONID"),
                "Expected Set-Cookie header with JSESSIONID but found none"
            );
        });

    }

    @Test
    public static void loginNegativeBadCredentials() {
        // Arrange
        String username = "ghost";
        String password = "not a passsword";

        // Act
        JavalinTest.test(app, (server, client) -> {
            Response response = client.post(
                    "/login",
                    Map.of(
                            "username", username,
                            "password", password
                    )
            );

            // Assert
            Assert.assertTrue(
                    response.code() == HttpStatus.UNAUTHORIZED.getCode()
            );
        });

    }

}
