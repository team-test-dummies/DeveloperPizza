package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;

import java.time.Duration;

public class RegisterStep {
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));

    // SET SCENE
    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        MainRunner.masterPage.get("http://localhost:8080/index.html");
        throw new SkipException("work in progress");
    }

    @And("User clicks on the register button")
    public void user_clicks_on_the_register_button() throws InterruptedException {
        MainRunner.loginPage.register_button();
    }

    // USER ENTERS FULL NAME
    @When("User enters {string} into full name field")
    public void user_enters_into_full_name_input(String string) {
        wait.until(ExpectedConditions.urlMatches("http://localhost:8080/pages/register.html"));
        MainRunner.registerPage.enter_full_name(string);
    }

    // USER ENTERS USERNAME
    @And("User enters {string} into username field")
    public void user_enters_into_username_input(String string) {
        MainRunner.registerPage.enter_username(string);
    }

    // USER ENTERS PASSWORD
    @And("User enters {string} into password field")
    public void user_enters_into_password_input(String string) {
        MainRunner.registerPage.enter_password(string);
    }

    // USER ENTERS PHONE NUMBER
    @And("User enters {string} into phone number field")
    public void user_enters_into_phone_number_input(String string) {
        MainRunner.registerPage.enter_phone_number(string);
    }

    // USER ENTERS EMAIL
    @And("User enters {string} into email field")
    public void user_enters_into_email_input(String string) {
        MainRunner.registerPage.enter_email(string);
    }

    // USER ENTERS LOCATION
    @And("User enters {string} into location field")
    public void user_enters_into_location_input(String string) {
        MainRunner.registerPage.enter_location(string);
    }

    // TEST(S)
    @Then("User clicks the sign up button")
    public void user_clicks_on_the_sign_up_button() {
        MainRunner.registerPage.sign_up_button();
    }
}