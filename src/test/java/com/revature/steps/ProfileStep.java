package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

import static com.revature.runner.MainRunner.*;

public class ProfileStep {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    @Then("User should see their profile content")
    public void user_should_see_their_profile_content() {
        Assert.assertTrue(MainRunner.profilePage.account_details());
    }

    // DELETE WITH VALID CREDENTIALS
    @When("User clicks the profile delete button")
    public void user_clicks_the_profile_delete_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/div[@id='accountInfo']/ul/li[6]/button[contains(text(), 'Delete')]")));
        profilePage.deleteProfileButton.click();
    }
    @When("A popup appears stating to input credentials to delete profile")
    public void a_popup_appears_stating_to_input_credentials_to_delete_profile() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//dialog[@id='delete-dialog']")));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(
                "//dialog/p"),
                "Enter your username and password to permanently delete your account"
        ));
        String actual = profilePage.dialogText.getText();
        Assert.assertEquals(actual, "Enter your username and password to permanently delete your account");
    }
    @When("User inputs their username")
    public void user_inputs_their_username() {
        profilePage.usernameDialogInput.sendKeys("rickmonald");
    }
    @When("User inputs their password")
    public void user_inputs_their_password() {
        profilePage.passwordDialogInput.sendKeys("guest");
    }
    @When("User clicks the delete button")
    public void user_clicks_the_delete_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//dialog/button[contains(text(), 'Delete')]")));
        profilePage.dialogBoxDeleteBtn.click();
    }
    @Then("User sees an alert stating the account was successfully deleted")
    public void user_sees_an_alert_stating_the_account_was_successfully_deleted() {
        wait.until(ExpectedConditions.alertIsPresent());
        String actual = driver.switchTo().alert().getText();
        Assert.assertEquals(actual, "Account successfully deleted");
        driver.switchTo().alert().accept();
    }
    @Then("User is navigated to the login page")
    public void user_is_navigated_to_the_login_page() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/"));
        String actual = driver.getCurrentUrl();
        Assert.assertEquals(actual, "http://localhost:8080/");
    }
    @Then("User attempts to login with valid credentials")
    public void user_attempts_to_login_with_valid_credentials() {
        loginPage.usernameInput.sendKeys("rickmonald");
        loginPage.passwordInput.sendKeys("guest");
        loginPage.loginButton.sendKeys(Keys.RETURN);
    }
    @Then("Users sees an error message stating invalid username or password")
    public void users_sees_an_error_message_stating_invalid_username_or_password() {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@id='error']"), "Invalid username or password"));
        String actual = loginPage.errorFlash.getText();
        Assert.assertEquals(actual, "Invalid username or password");
    }
    // CLOSE DELETE DIALOG
    @When("User clicks the cancel button")
    public void user_clicks_the_cancel_button() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//dialog/button[contains(text(), 'Cancel')]")));
        profilePage.dialogBoxCancelBtn.click();
    }
    @Then("The popup is closed")
    public void the_popup_is_closed() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//dialog[@id='delete-dialog']")));
    }

    // DELETE WITH INVALID USERNAME
    @When("User inputs an invalid username")
    public void user_inputs_an_invalid_username() {
        profilePage.usernameDialogInput.sendKeys("invalid");
    }
    @Then("User sees an alert stating username or password is incorrect")
    public void user_sees_an_alert_stating_username_or_password_is_incorrect() {
        wait.until(ExpectedConditions.alertIsPresent());
        String actual = driver.switchTo().alert().getText();
        Assert.assertEquals(actual, "Invalid username/password");
    }
    // DELETE WITHOUT USERNAME/PASSWORD
    @Then("User sees an alert stating username or password is required")
    public void user_sees_an_alert_stating_username_or_password_is_required() {
        wait.until(ExpectedConditions.alertIsPresent());
        String actual = driver.switchTo().alert().getText();
        Assert.assertEquals(actual, "Username/Password required");
    }
    // DELETE WITH INVALID PASSWORD
    @When("User inputs an invalid password")
    public void user_inputs_an_invalid_password() {
        profilePage.passwordDialogInput.sendKeys("invalid");
    }

}
