package com.revature.dao;

import com.revature.PrototypingApp;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

public class TemplatesDaoTest {

    @BeforeMethod
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterMethod
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    // just a spot check to be replaced
    @Test
    public void testy() throws SQLException {
        Assert.assertEquals(TemplatesDao.getTemplates(), null);
    }

}
