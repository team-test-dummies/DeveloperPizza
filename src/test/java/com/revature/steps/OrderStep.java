package com.revature.steps;

import com.revature.runner.MainRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class OrderStep {
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(5));

    // SET SCENE
    @Given("The User is logged in")
    public void the_user_is_logged_in() {
        MainRunner.masterPage.get("http://localhost:8080");
        MainRunner.loginPage.enter_username("rickmonald");
        MainRunner.loginPage.enter_password("guest");
        MainRunner.loginPage.login_button();
    }

    @And("the user is on the order page")
    public void the_user_is_on_the_order_page() {
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/pages/userprofile.html"));
        MainRunner.profilePage.createOrderButton.click();
        wait.until(ExpectedConditions.urlToBe("http://localhost:8080/pages/startorder.html"));
    }

    // USER SELECT PREMADE(S)
    @When("User selects premades options")
    public void user_selects_premades_options() {
        MainRunner.orderPage.randPremade_selection();
    }

    // USER SELECT LANGUAGE(S)
    @And("User selects languages options")
    public void user_selects_languages_options() {
        MainRunner.orderPage.randLanguage_selection();
    }

    // USER SELECT TOOL(S)
    @And("the user selects tools options")
    public void user_selects_tools_options() {
        MainRunner.orderPage.randTools_selection();
    }

    // USER SELECT EDUCTION
    @And("the user selects education options")
    public void user_selects_education_options() {
        MainRunner.orderPage.randEducation_selection();
    }


    // USER ENTERS SALARY
    @And("the user enters {string} into salary field")
    public void user_enters_salary(String salary) {
        MainRunner.orderPage.salarySelection.sendKeys(salary);
    }


    @When("the user selects test automation premade option")
    public void theUserSelectsTestAutomationPremadeOption() {
        Select premadeSelection = new Select(MainRunner.orderPage.premadeSelect);
        premadeSelection.selectByVisibleText("Test Automation Engineer");
    }

    @And("the user is on the userprofile page")
    public void theUserIsOnTheUserprofilePage() {
        boolean onUserProfile = wait.until(ExpectedConditions.urlToBe("http://localhost:8080/pages/userprofile.html"));
        Assert.assertTrue(onUserProfile);
    }

    @When("they select all the options")
    public void theySelectAllTheOptions() {
    }

    @Then("the user clicks on order and a modal is opened")
    public void theUserClicksOnOrderAndAModalIsOpened() {
        MainRunner.orderPage.order_button();
        MainRunner.wait.until(ExpectedConditions.visibilityOf(MainRunner.orderPage.orderModal));
    }

    @Then("the user clicks on place order and a modal is closed")
    public void theUserClicksOnPlaceOrderAndAModalIsClosed() {
        MainRunner.orderPage.placeOrderButton.click();
        boolean itsGone =MainRunner.wait.until(ExpectedConditions.invisibilityOf(MainRunner.orderPage.orderModal));
        Assert.assertTrue(itsGone);
    }

    @When("the user selects all of the languages")
    public void theUserSelectsAllOfTheLanguages() {
        for (WebElement l: MainRunner.orderPage.languagesList) {
            l.click();
        }
    }

    @And("the user selects all of the tools")
    public void theUserSelectsAllOfTheTools() {
        for (WebElement t: MainRunner.orderPage.toolsList) {
            t.click();
        }
    }


    @Then("the tally equals the same number of languages")
    public void theTallyEqualsTheSameNumberOfLanguages() {
        Assert.assertEquals(MainRunner.orderPage.languagesList.size(), MainRunner.orderPage.orderTally.size());
    }

    @Then("the tally equals the same number of tools")
    public void theTallyEqualsTheSameNumberOfTools() {
        Assert.assertEquals(MainRunner.orderPage.toolsList.size(), MainRunner.orderPage.orderTally.size());
    }

    @Then("the tally equals the total number of inputs")
    public void theTallyEqualsTheTotalNumberOfInputs() {
        int toolSize = MainRunner.orderPage.toolsList.size();
        int languageSize = MainRunner.orderPage.languagesList.size();
        Assert.assertEquals((toolSize+languageSize), MainRunner.orderPage.orderTally.size());
    }

    @When("the user selects a language")
    public void theUserSelectsALanguage() {
    }

    @And("the user selects a tool")
    public void theUserSelectsATool() {
    }

    @And("the user adds a name")
    public void theUserAddsAName() {
        MainRunner.orderPage.nameInput.sendKeys("10X Developer");
    }
}
