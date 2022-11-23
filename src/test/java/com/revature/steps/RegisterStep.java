package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class RegisterStep {
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));

    // SET SCENE
    @Given("User clicks on the create profile button")
    public void user_clicks_on_the_create_profile_button() {
        WebElement registerButton = MainRunner.wait.until(ExpectedConditions.visibilityOf(MainRunner.loginPage.registerButton));
        registerButton.click();
    }

    // USER ENTERS FULL NAME
    @When("User enters {string} into full name field")
    public void user_enters_into_full_name_input(String fullname) {
        MainRunner.registerPage.enter_full_name(fullname);
    }

    // USER ENTERS USERNAME
    @And("User enters {string} into username field")
    public void user_enters_into_username_input(String username) {
        MainRunner.registerPage.enter_username(username);
    }

    // USER ENTERS PASSWORD
    @And("User enters {string} into password field")
    public void user_enters_into_password_input(String password) {
        MainRunner.registerPage.enter_password(password);
    }

    // USER ENTERS PHONE NUMBER
    @And("User enters {string} into phone number field")
    public void user_enters_into_phone_number_input(String phonenumber) {
        MainRunner.registerPage.enter_phone_number(phonenumber);
    }

    // USER ENTERS EMAIL
    @And("User enters {string} into email field")
    public void user_enters_into_email_input(String email) {
        MainRunner.registerPage.enter_email(email);
    }

    // USER ENTERS LOCATION
/*
    @And("User enters {string} into location field")
    public void user_enters_into_location_input(String location) {
        MainRunner.registerPage.enter_location(location);
    }
*/

    // USER CLICKS SIGN UP BUTTON
    @When("User clicks the sign up button")
    public void user_clicks_on_the_sign_up_button() {
        MainRunner.registerPage.sign_up_button();
    }

    // TEST(S)
    @Then("User sees an alert message {string}")
    public void user_sees_an_alert_message(String message) {
        wait.until(ExpectedConditions.alertIsPresent());
        String actualAlert = MainRunner.driver.switchTo().alert().getText();
        Assert.assertEquals(actualAlert, message);
    }
}
