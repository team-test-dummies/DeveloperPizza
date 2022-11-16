package com.revature.controller;

import com.revature.App;
import com.revature.PrototypingApp;
import com.revature.dao.AuthDao;
import com.revature.dao.UserDao;
import com.revature.data.enums.Role;
import com.revature.data.records.Credentials;
import com.revature.data.records.User;
import io.javalin.Javalin;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import static org.mockito.Mockito.when;

public class MockitoControllerTest {

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
    public void testy() throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {

        Credentials fakeCreds = new Credentials("fake", "phoney");

        User fakeUser = new User(
                0,
                Role.ADMIN,
                "fake",
                "fake",
                "fake",
                "fake",
                "fake",
                "fake"
        );

        // don't forget to add the mockito-inline dependency where mockto-core is
        try (MockedStatic<AuthDao> utilities = Mockito.mockStatic(AuthDao.class)) {
            utilities.when(() -> AuthDao.findUser(fakeCreds)).thenReturn(
                    fakeUser
            );
            // inside this try block, calls to AuthDao.findUser(fakeCreds) will be
            // intercepted and a return value of fakeUser will be sent instead
            // via MAGIC! (seriously, this is programming wizadry)
            Assert.assertEquals(AuthDao.findUser(fakeCreds), fakeUser);
        }

    }
}
