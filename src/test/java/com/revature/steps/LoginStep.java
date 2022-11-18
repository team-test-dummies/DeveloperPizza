package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginStep {
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));

    // SET SCENE
    @Given("User on the login page")
    public void user_on_the_login_page() {
        MainRunner.masterPage.get("http://localhost:8080/index.html");
    }

    // USER ENTERS USERNAME
    @When("User enters {string} into username input")
    public void user_enters_into_username_input(String string) {
        MainRunner.loginPage.enter_username(string);
    }

    // USER ENTERS PASSWORD
    @And("User enters {string} into password input")
    public void user_enters_into_password_input(String string) {
        MainRunner.loginPage.enter_password(string);
    }

    // USER CLICK LOGIN BUTTON
    @And("User clicks on the login button")
    public void user_clicks_on_the_login_button() {
        MainRunner.loginPage.login_buttion();
    }

    // TEST(S)
    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/pages/startorder.html"));
        Assert.assertEquals(MainRunner.driver.getCurrentUrl(), "http://localhost:8080/pages/startorder.html");
    }

    @Then("An alert should be displayed with the message {string}")
    public void an_alert_should_be_displayed_with_the_message(String string) {
        wait.until(ExpectedConditions.alertIsPresent());
        String actualAlert = MainRunner.masterPage.getAlert();
        Assert.assertEquals(actualAlert, string);
    }
}
