package com.revature.dao;

import com.revature.App;
import com.revature.PrototypingApp;
import com.revature.data.enums.Education;
import com.revature.data.records.Order;
import com.revature.data.records.StartOrder;
import com.revature.data.records.Template;
import io.javalin.Javalin;
import org.mockito.MockedStatic;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class TemplatesDaoTest {
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

    // just a spot check to be replaced
    @Test
    public void testy() throws SQLException {
        Assert.assertEquals(TemplatesDao.getTemplates(), null);
    }

    //GET TEMPLATE(S)
    @Test
    public void getTemplatesPositive() throws SQLException {
        List<Template> templates = TemplatesDao.getTemplates();
        try(MockedStatic<TemplatesDao> mock = org.mockito.Mockito.mockStatic(TemplatesDao.class)) {
            mock.when(TemplatesDao::getTemplates).thenReturn(List.of(templates));
            List<Template> actual = TemplatesDao.getTemplates();
            Assert.assertEquals(actual, List.of(templates));
        }
    }

    // GET LANGUAGE(S)
    @Test
    public void getLanguagesPositive() throws SQLException {
        List<String> languages = TemplatesDao.getLanguages();
        try (MockedStatic<TemplatesDao> mock = org.mockito.Mockito.mockStatic(TemplatesDao.class)) {
            mock.when(TemplatesDao::getLanguages).thenReturn(List.of(languages));
            List<String> actual = TemplatesDao.getLanguages();
            Assert.assertEquals(actual, List.of(languages));
        }
    }

    // GET TOOL(S)
    @Test
    public void getToolsPositive() throws SQLException {
        List<String> tools = TemplatesDao.getTools();
        try(MockedStatic<TemplatesDao> mock = org.mockito.Mockito.mockStatic(TemplatesDao.class)) {
            mock.when(TemplatesDao::getTools).thenReturn(List.of(tools));
            List<String> actual = TemplatesDao.getTools();
            Assert.assertEquals(actual, List.of(tools));
        }
    }
}
