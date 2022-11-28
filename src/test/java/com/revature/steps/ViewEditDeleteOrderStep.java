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

public class ViewEditDeleteOrderStep {
    WebDriverWait wait = new WebDriverWait(MainRunner.driver, Duration.ofSeconds(10));
    @Given("User is logged in")
    public void the_user_is_logged_in() {
        MainRunner.masterPage.get("http://localhost:8080/");
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

    // EDIT ORDER
    @When("User clicks edit button")
    public void user_clicks_edit_button() {
        WebElement editOrderButton = MainRunner.wait.until(ExpectedConditions.elementToBeClickable(MainRunner.profilePage.editOrderButton));
        editOrderButton.click();
    }

    @And("User edits order name")
    public void user_edits_order_name() {
        MainRunner.profilePage.editName.clear();
        MainRunner.profilePage.editName.sendKeys("Edit Test");
    }

    @And("User edits language option")
    public void user_edits_language_option() {
        WebElement languageClickable = MainRunner.wait.until(ExpectedConditions.elementToBeClickable(MainRunner.profilePage.editLanguage));
        languageClickable.click();
    }

    @And("User edits tool option")
    public void user_edits_tool_option() {
        WebElement toolClickable = MainRunner.wait.until(ExpectedConditions.elementToBeClickable(MainRunner.profilePage.editTool));
        toolClickable.click();
    }

    @And("User edits education option")
    public void user_edits_education_option() {
        Select education = new Select(MainRunner.profilePage.editEducation);
        education.selectByValue("MASTERS");
    }

    @And("User edits salary amount")
    public void user_edits_salary_amount() {
        MainRunner.profilePage.editSalary.clear();
        MainRunner.profilePage.editSalary.sendKeys("70000");
    }

    @And("User clicks confirm button")
    public void user_clicks_confirm_button() {
        WebElement confirmButton = MainRunner.wait.until(ExpectedConditions.elementToBeClickable(MainRunner.profilePage.confirmOrderButton));
        confirmButton.click();
    }

    @When("User clicks delete button")
    public void user_clicks_delete_button() {
        MainRunner.profilePage.deleteOrderButton.click();
    }

    // TEST(S)

    @Then("User confirms profile content")
    public void user_confirms_profile_content() {
        Assert.assertEquals(MainRunner.profilePage.accountUsername.getText(), "rickmonald");
        Assert.assertEquals(MainRunner.profilePage.accountFullname.getText(), "Rick Monald's");
        Assert.assertEquals(MainRunner.profilePage.accountPhonenumber.getText(), "000-867-5309");
        Assert.assertEquals(MainRunner.profilePage.accountEmail.getText(), "rick@rickmonalds.com");
        Assert.assertEquals(MainRunner.profilePage.accountLocation.getText(), "Minnesota");
    }

    @Then("User should see their order list")
    public void user_should_see_their_order_list() {
        Assert.assertTrue(MainRunner.profilePage.order_list());
    }

    @Then("User should see the edit order popup")
    public void user_should_see_the_edit_order_popup() {
        Assert.assertTrue(MainRunner.profilePage.editOrderBox.isDisplayed());
    }

    @Then("User should see edited order")
    public void user_should_see_edited_order() {
        MainRunner.wait.until(ExpectedConditions.textToBePresentInElement(MainRunner.profilePage.orderName, "Edit Test"));
        Assert.assertEquals(MainRunner.profilePage.orderName.getText(), "Edit Test");
        MainRunner.wait.until(ExpectedConditions.textToBePresentInElement(MainRunner.profilePage.orderLanguage, "HTML"));
        Assert.assertEquals(MainRunner.profilePage.orderLanguage.getText(), "HTML");
        MainRunner.wait.until(ExpectedConditions.textToBePresentInElement(MainRunner.profilePage.orderTool, "IntelliJ"));
        Assert.assertEquals(MainRunner.profilePage.orderTool.getText(), "IntelliJ");
        MainRunner.wait.until(ExpectedConditions.textToBePresentInElement(MainRunner.profilePage.orderEducation, "MASTERS"));
        Assert.assertEquals(MainRunner.profilePage.orderEducation.getText(), "MASTERS");
        MainRunner.wait.until(ExpectedConditions.textToBePresentInElement(MainRunner.profilePage.orderSalary, "70000"));
        Assert.assertEquals(MainRunner.profilePage.orderSalary.getText(), "70000");
    }
}
