package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ProfileStep {
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));

    // SET SCENE
    @Given("The User clicks on the profile button")
    public void the_user_clicks_on_the_profile_button() {
        MainRunner.masterPage.get("http://localhost:8080/index.html");
        MainRunner.loginPage.enter_username("rickmonald");
        MainRunner.loginPage.enter_password("guest");
        MainRunner.loginPage.login_button();
        WebElement webElement = MainRunner.wait.until(ExpectedConditions.visibilityOf(MainRunner.orderPage.profileButton));
        webElement.click();
    }

    // CHECK PROFILE PAGE
    @When("The User is on the profile page")
    public void the_user_is_on_the_profile_page() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/pages/userprofile.html"));
        Assert.assertEquals(MainRunner.driver.getCurrentUrl(), "http://localhost:8080/pages/userprofile.html");
    }

    // TEST(S)
    @Then("The User should see their account details")
    public void the_user_should_see_their_account_details() {
        Assert.assertTrue(MainRunner.profilePage.account_details());
    }

    @Then("The User should see their order history")
    public void the_user_should_see_their_order_history() {
        Assert.assertTrue(MainRunner.profilePage.order_history());
    }
}
