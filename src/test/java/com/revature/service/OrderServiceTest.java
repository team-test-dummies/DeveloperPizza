package com.revature.service;

import com.revature.PrototypingApp;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class OrderServiceTest {

    @BeforeMethod
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterMethod
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    @Test
    public void getOrdersPositive() {
        throw new SkipException("unimplemented");
    }

    public void postOrderPositive() {
        throw new SkipException("unimplemented");
    }
}
