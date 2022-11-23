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

public class ProfileStep {
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));

    // SET SCENE
    @Given("User is logged in")
    public void the_user_is_logged_in() {
        MainRunner.masterPage.get("http://localhost:8080/index.html");
        MainRunner.loginPage.enter_username("rickmonald");
        MainRunner.loginPage.enter_password("guest");
        MainRunner.loginPage.login_button();
    }

    // CHECK PROFILE PAGE
    @When("User is on the profile page")
    public void user_is_on_the_profile_page() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/pages/userprofile.html"));
        Assert.assertEquals(MainRunner.driver.getCurrentUrl(), "http://localhost:8080/pages/userprofile.html");
    }

    // CHECK EACH PROFILE CONTENT
    @And("User confirms username is displayed")
    public void user_confirms_username_is_displayed() {
        Assert.assertEquals(MainRunner.profilePage.accountUsername.getText(), "rickmonald");
    }

    @And("User confirms fullname is displayed")
    public void user_confirms_fullname_is_displayed() {
        Assert.assertEquals(MainRunner.profilePage.accountFullname.getText(), "Rick Monald's");
    }

    @And("User confirms phonenumber is displayed")
    public void user_confirms_phonenumber_is_displayed() {
        Assert.assertEquals(MainRunner.profilePage.accountPhonenumber.getText(), "000-867-5309");
    }

    @And("User confirms email is displayed")
    public void user_confirms_email_is_displayed() {
        Assert.assertEquals(MainRunner.profilePage.accountEmail.getText(), "rick@rickmonalds.com");
    }

    @And("User confirms location is displayed")
    public void user_confirms_location_is_displayed() {
        Assert.assertEquals(MainRunner.profilePage.accountLocation.getText(), "Minnesota");
    }

    // CHECK ORDER SIZE

    // TEST(S)
    @Then("User should see their profile content")
    public void user_should_see_their_profile_content() {
        Assert.assertTrue(MainRunner.profilePage.account_details());
    }

    @Then("User should see their order list")
    public void user_should_see_their_order_list() {
        Assert.assertTrue(MainRunner.profilePage.order_list());
    }
}
