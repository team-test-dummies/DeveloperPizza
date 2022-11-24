package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static com.revature.runner.MainRunner.*;

public class RegisterStep {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

    // BACKGROUND

    @Given("User is on the register page")
    public void user_is_on_the_register_page() {
        driver.get("http://localhost:8080/pages/register.html");
        Assert.assertEquals(driver.getCurrentUrl(), "http://localhost:8080/pages/register.html");
    }

    // REGISTER WITH VALID INPUTS

    @When("User enters {string} into full name field")
    public void user_enters_into_full_name_field(String string) {
        registerPage.fullNameInput.sendKeys("Jane Doe");
    }
    @When("User enters {string} into username field")
    public void user_enters_into_username_field(String string) {
        registerPage.usernameInput.sendKeys("Jane123");
    }
    @When("User enters {string} into password field")
    public void user_enters_into_password_field(String string) {
        registerPage.passwordInput.sendKeys("Password1");
    }
    @When("User enters {string} into phone number field")
    public void user_enters_into_phone_number_field(String string) {
        registerPage.phoneNumberInput.sendKeys("555-555-5555");
    }
    @When("User enters {string} into email field")
    public void user_enters_into_email_field(String string) {
        registerPage.emailInput.sendKeys("jane@gmail.com");
    }
    @When("User enters {string} into location field")
    public void user_enters_into_location_field(String string) {
        registerPage.locationInput.sendKeys("Georgia");
    }
    @When("User clicks the sign up button")
    public void user_clicks_the_sign_up_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='signup']")));
        registerPage.signupButton.click();
    }
    @Then("User sees a popup for successful registration")
    public void user_sees_a_popup_for_successful_registration() {
        wait.until(ExpectedConditions.alertIsPresent());
        String actual = driver.switchTo().alert().getText();
        Assert.assertEquals(actual, "Registration successful!" + "\n" + "Login to your new account");
    }
    @Then("User clicks Ok")
    public void user_clicks_ok() {
        driver.switchTo().alert().accept();
    }
    @Then("User is redirected to the login page")
    public void user_is_redirected_to_the_login_page() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/index.html"));
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, "http://localhost:8080/index.html");
    }

    // REGISTER WITHOUT FULL NAME

    @Then("User sees an error message for invalid full name")
    public void user_sees_an_error_message_for_invalid_full_name() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div[2]//div/p[@id='registerErr']")));
        Assert.assertEquals(registerPage.errorMessage.getText(), "Full name is required");
    }

    // REGISTER WITH INVALID USERNAME

    @When("User enters null into username field")
    public void user_enters_null_into_username_field() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("User sees an error message for invalid username")
    public void user_sees_an_error_message_for_invalid_username() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div[2]//div/p[@id='registerErr']")));
        Assert.assertEquals(registerPage.errorMessage.getText(),
                "Username must 6-16 characters long" + "\n" +
                        "Cannot include special characters (@, $, !, *, etc)"
        );
    }
    @When("User enters jane into username field")
    public void user_enters_jane_into_username_field() {
        registerPage.usernameInput.sendKeys("jane");
    }
    @When("User enters username$ into username field")
    public void user_enters_username$_into_username_field() {
        registerPage.usernameInput.sendKeys("username$");
    }
    @When("User enters averylongusername into username field")
    public void user_enters_averylongusername_into_username_field() {
        registerPage.usernameInput.sendKeys("averylongusername");
    }

    // REGISTER WITH INVALID PASSWORD


    // REGISTER WITH INVALID PHONE NUMBER


    // REGISTER WITH INVALID EMAIL


    // REGISTER WITHOUT LOCATION

    @Then("User sees an error message for invalid location")
    public void user_sees_an_error_message_for_invalid_location() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div[2]//div/p[@id='registerErr']")));
        Assert.assertEquals(registerPage.errorMessage.getText(), "Location is required");
    }
}