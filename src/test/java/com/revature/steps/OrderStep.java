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

public class OrderStep {
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));

    // SET SCENE
    @Given("The User is logged in")
    public void the_user_is_logged_in() {
        MainRunner.masterPage.get("http://localhost:8080/index.html");
        MainRunner.loginPage.enter_username("rickmonald");
        MainRunner.loginPage.enter_password("guest");
        MainRunner.loginPage.login_button();
    }

    @And("The User is on the order page")
    public void the_user_is_on_the_order_page() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/pages/startorder.html"));
        Assert.assertEquals(MainRunner.driver.getCurrentUrl(), "http://localhost:8080/pages/startorder.html");
    }

    // USER SELECT PREMADE(S)
    @When("The User selects premades options")
    public void the_user_selects_premades_options() {
        MainRunner.orderPage.randPremade_selection();
    }

    // USER SELECT LANGUAGE(S)
    @And("The User selects languages options")
    public void the_user_selects_languages_options() {
        MainRunner.orderPage.randLanguage_selection();
    }

    // USER SELECT TOOL(S)
    @And("The User selects tools options")
    public void the_user_selects_tools_options() {
        MainRunner.orderPage.randTools_selection();
    }

    // USER SELECT EDUCTION
    @And("The User selects education options")
    public void the_user_selects_education_options() {
        MainRunner.orderPage.randEducation_selection();
    }

    // USER ENTERS LOCATION
    @And("The User enters {string} into location field")
    public void the_user_enters_location(String location) {
        MainRunner.orderPage.enter_location(location);
    }

    // USER ENTERS SALARY
    @And("The User enters {string} into salary field")
    public void the_user_enters_salary(String salary) {
        MainRunner.orderPage.enter_salary(salary);
    }

    // TEST(S)
    @Then("The User clicks on order button")
    public void the_user_clicks_on_order_button() {
        MainRunner.orderPage.order_button();
    }
}
