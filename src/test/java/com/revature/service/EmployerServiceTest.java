package com.revature.service;

import com.revature.PrototypingApp;

import com.revature.data.records.EditProfile;
import com.revature.data.records.Employer;
import com.revature.data.records.RegisterInfo;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class EmployerServiceTest {

    @BeforeTest
    public void setup() throws SQLException {
        PrototypingApp.setup();
    }

    @AfterTest
    public void cleanup() throws SQLException {
        PrototypingApp.cleanup();
    }

    @Test
    public void getAllEmployersTest() {
        //Arrange

        //Act

        //Assert

    }

    @Test
    public void registerEmployerTestPositive() throws SQLException {
        //Arrange

        RegisterInfo newEmployerInfo = new RegisterInfo("employer", "John Doe", "jonnie",
                "password", "555-555-5555", "john@gmail.com", "Georgia");

        //Act
        int expected = 1;
        int actual = EmployerService.registerEmployer(newEmployerInfo);

        //Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void registerEmployerTestNegative() throws SQLException {
        //Arrange
        RegisterInfo noInfo = new RegisterInfo("", "", "", "", "", "",
                "");

        //Act
        int expected = 0;
        int actual = EmployerService.registerEmployer(noInfo);

        //Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void getEmployerByUsernameTest() throws SQLException {
        //Arrange
        Employer madisonKora = new Employer(1, "employer", "madison_kora", "madkor436",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��", "505-684-9399", "madkor436@company.net", "New Mexico");
        //Act
        Employer expected = madisonKora;
        Employer actual = EmployerService.getEmployerByUsername("madkor436");

        //Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void editEmployerTest() throws SQLException, IOException {
        //Arrange
        EditProfile editedProfile = new EditProfile("madison_kora", "madkor436",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��", "555-555-5555", "madkor436@company.net", "California");

        //Act
        EditProfile expected = editedProfile;
        EditProfile actual = EmployerService.editEmployer(editedProfile);

        //Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void editEmployerTestNegative() throws SQLException, IOException {
        //Arrange
        EditProfile editedProfile = new EditProfile("madison_kora", "INVALID",
                "k�5�O���\u0015D�a=�z��kl\\q�I���\u000F�x��", "555-555-5555", "madkor436@company.net", "California");

        //Act
        RuntimeException expected = new com.revature.data.enums.exception.AccountUnsuccessfullyEditedException("Profile was not edited");
        EditProfile actual = EmployerService.editEmployer(editedProfile);

        //Assert
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void removeEmployerTest() {
        //Arrange

        //Act

        //Assert
    }
}
