package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    @When("User enters a full name")
    public void user_enters_a_full_name() {
        registerPage.fullNameInput.sendKeys("Jane Doe");
    }
    @When("User enters a username")
    public void user_enters_a_username() {
        registerPage.usernameInput.sendKeys("Jane123");
    }
    @When("User enters a password")
    public void user_enters_a_password() {
        registerPage.passwordInput.sendKeys("Password1");
    }
    @When("User enters a phone number")
    public void user_enters_a_phone_number() {
        registerPage.phoneNumberInput.sendKeys("555-555-5555");
    }
    @When("User enters an email")
    public void user_enters_an_email() {
        registerPage.emailInput.sendKeys("jane@gmail.com");
    }
    @When("User enters a location")
    public void user_enters_a_location() {
        registerPage.locationInput.sendKeys("Georgia");
    }
    @When("User clicks the sign up button")
    public void user_clicks_the_sign_up_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='signup']")));
        registerPage.signupButton.sendKeys(Keys.RETURN);
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
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div/div/div[2]//div/p[@id='registerErr']"))
        );
        Assert.assertEquals(registerPage.errorMessage.getText(), "Full name is required");
    }

    // REGISTER WITH INVALID USERNAME

    @Then("User sees an error message for invalid username")
    public void user_sees_an_error_message_for_invalid_username() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div/div/div[2]//div/p[@id='registerErr']"))
        );
        Assert.assertEquals(registerPage.errorMessage.getText(),
                "Username must be 6-16 characters long" + "\n" +
                "Cannot include special characters (@, $, !, *, etc)"
        );
    }
    @When("User enters special characters in their username")
    public void user_enters_special_characters_in_their_username() {
        registerPage.usernameInput.sendKeys("jane$%&");
    }
    @When("User enters a short username")
    public void user_enters_a_short_username() {
        registerPage.usernameInput.sendKeys("jane");
    }
    @When("User enters a long username")
    public void user_enters_a_long_username() {
        registerPage.usernameInput.sendKeys("averylongusername");
    }

    // REGISTER WITH INVALID PASSWORD

    @Then("User sees an error message for invalid password")
    public void user_sees_an_error_message_for_invalid_password() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div[2]//div/p[@id='registerErr']")));
        Assert.assertEquals(registerPage.errorMessage.getText(),
                "Password must be 6-16 characters long" + "\n" +
                "Must contain atleast one upppercase letter and one number"
        );
    }
    @When("User enters one number in password")
    public void user_enters_one_number_in_password() {
        registerPage.passwordInput.sendKeys("password1");
    }
    @When("User enters one uppercase letter in password")
    public void user_enters_one_uppercase_letter_in_password() {
        registerPage.passwordInput.sendKeys("Password");
    }
    @When("User enters a short password")
    public void user_enters_a_short_password() {
        registerPage.passwordInput.sendKeys("pass");
    }
    @When("User enters a long password")
    public void user_enters_a_long_password() {
        registerPage.passwordInput.sendKeys("averylongpassword");
    }

    // REGISTER WITH INVALID PHONE NUMBER

    @Then("User sees an error message for invalid phone number")
    public void user_sees_an_error_message_for_invalid_phone_number() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div[2]//div/p[@id='registerErr']")));
        Assert.assertEquals(registerPage.errorMessage.getText(), "A valid phone number is required");
    }@When("User enters NaN as their phone number")
    public void user_enters_na_n_as_their_phone_number() {
        registerPage.phoneNumberInput.sendKeys("phonenumber");
    }
    @When("User enters too many digits as their phone number")
    public void user_enters_too_many_digits_as_their_phone_number() {
        registerPage.phoneNumberInput.sendKeys("555-555-555555555");
    }

    // REGISTER WITH INVALID EMAIL

    @Then("User sees an error message for invalid email")
    public void user_sees_an_error_message_for_invalid_email() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div[2]//div/p[@id='registerErr']")));
        Assert.assertEquals(registerPage.errorMessage.getText(), "A valid email is required");
    }
    @When("User enters email with at sign")
    public void user_enters_email_with_at_sign() {
        registerPage.emailInput.sendKeys("jane@");
    }
    @When("User enters email without at sign")
    public void user_enters_email_without_at_sign() {
        registerPage.emailInput.sendKeys("janegmail.com");
    }
    @When("User enters extra periods in their email")
    public void user_enters_extra_periods_in_their_email() {
        registerPage.emailInput.sendKeys("jane@.gmail.com.");
    }

    // REGISTER WITHOUT LOCATION

    @Then("User sees an error message for invalid location")
    public void user_sees_an_error_message_for_invalid_location() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/div[2]//div/p[@id='registerErr']")));
        Assert.assertEquals(registerPage.errorMessage.getText(), "Location is required");
    }
}