package com.revature.pages;

import com.revature.runner.MainRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class OrderPage {
    public OrderPage (WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // PREMADE SELECTIONS
    @FindBy(xpath = "//div[1]//div[1]//div[1]/div[1]/input[1]")
    private WebElement premadeSelection;

    // LANGUAGE SELECTION
    @FindBy(xpath = "//div[1]//div[1]//div[2]/div[1]/input[1]")
    private WebElement languageSelection;

    // TOOLS SELECTION
    @FindBy(xpath = "//div[1]//div[1]//div[3]/div[1]/input[1]")
    private WebElement toolsSelection;

    // EDUCATION SELECTION
    @FindBy(xpath = "//select[1]")
    private WebElement educationSelection;

    // LOCATION SELECTION
    @FindBy(xpath = "//input[@id='location']")
    private WebElement locationSelection;

    // SALARY SELECTION
    @FindBy(xpath = "//input[@id='salary']")
    private WebElement salarySelection;

    // ORDER BUTTON
    @FindBy(xpath = "//button[contains(text(),'Order')]")
    private WebElement orderButton;

    // FUNCTIONALITY
    public void randPremade_selection() {
        List<WebElement> premadeSelections = MainRunner.driver.findElements(By.xpath("//div[1]//div[1]//div[1]/div[1]/input[1]"));
        WebElement randCheckbox = premadeSelections.get(new Random().nextInt(premadeSelections.size()));
    }

    public void randLanguage_selection() {
        List<WebElement> languageSelections = MainRunner.driver.findElements(By.xpath("//div[1]//div[1]//div[2]/div[1]/input[1]"));
        WebElement randCheckbox = languageSelections.get(new Random().nextInt(languageSelections.size()));
    }

    public void randTools_selection() {
        List<WebElement> toolsSelections = MainRunner.driver.findElements(By.xpath("//div[1]//div[1]//div[3]/div[1]/input[1]"));

    }

    public void randEducation_selection() {
        Select select = new Select(educationSelection);
        select.selectByIndex(MainRunner.randGenerator(1, 6));
    }

    public void enter_location(String location) {
        locationSelection.sendKeys(location);
    }

    public void enter_salary(String salary) {
        salarySelection.sendKeys(salary);
    }

    public void order_button() {
        orderButton.click();
    }
}
