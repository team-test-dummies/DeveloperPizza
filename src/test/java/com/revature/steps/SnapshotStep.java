package com.revature.steps;

import com.revature.pages.LoginPage;
import com.revature.pages.ProfilePage;
import com.revature.runner.MainRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Data;
import io.cucumber.java.it.Ma;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.reporters.jq.Main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SnapshotStep {


    TakesScreenshot screenshoter = (TakesScreenshot) MainRunner.driver;

    private static void replaceIfExists(File file, File target) throws IOException {
        target.delete();
        file.renameTo(target);
        file.createNewFile();
    }

    @Then("^Take a picture of (\\w+)$")
    public void takeAPictureOf(String filename) throws IOException {
        File screenshot = screenshoter.getScreenshotAs(OutputType.FILE);
        File target = new File("target/" + filename + ".png");
        replaceIfExists(screenshot, target);
    }

    @When("User clicks on order edit button")
    public void user_clicks_on_order_edit_button() {
        ProfilePage page = new ProfilePage(MainRunner.driver);
        page.editOrderButton.click();
    }

    @When("User clicks on confirm")
    public void user_clicks_on_confirm() {
        ProfilePage page = new ProfilePage(MainRunner.driver);
        page.confirmOrderButton.click();
    }
}
