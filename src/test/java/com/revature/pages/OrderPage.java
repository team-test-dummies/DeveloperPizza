package com.revature.pages;

import com.revature.runner.MainRunner;
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
    @FindBy(xpath = "//*[@id=\"premade\"]")
    public WebElement premadeSelect;

    // Languages list
    @FindBy(xpath = "//*[@id=\"toppings\"]/div/label/input")
    public List<WebElement> languagesList;
    // Tools list
    @FindBy(xpath = "//*[@id=\"tools\"]/div/label/input")
    public List<WebElement> toolsList;
    @FindBy(xpath = "//*[@id=\"ordertally\"]/li")
    public List<WebElement> orderTally;
    // EDUCATION SELECTION
    @FindBy(xpath = "//select[1]")
    public WebElement educationSelection;

    // SALARY SELECTION
    @FindBy(xpath = "//input[@id='salary']")
    public WebElement salarySelection;

    // ORDER BUTTON
    @FindBy(xpath = "//button[contains(text(),'Order')]")
    public WebElement orderButton;

    // LOGOUT BUTTON
    @FindBy(xpath = "//div//ul/li[2]")
    public WebElement logoutButton;

    // PROFILE BUTTON
    @FindBy(xpath = "//div//ul/li[1]")
    public WebElement profileButton;
    @FindBy(xpath = "//button[@id='place-order']")
    public WebElement placeOrderButton;

    @FindBy(xpath = "//button[@id='cancel']")
    public WebElement cancelOrderButton;
    @FindBy(xpath = "//*[@id=\"orderModal\"]/div/div")
    public WebElement orderModal;

    @FindBy(xpath = "//input[@id='name']")
    public WebElement nameInput;
    // FUNCTIONALITY
    public void randPremade_selection() {
        Select premadeSelection = new Select(MainRunner.orderPage.premadeSelect);
        int index = (new Random().nextInt(premadeSelection.getOptions().size()));
        premadeSelection.selectByIndex(index);

    }

    public void randLanguage_selection() {
        WebElement randCheckbox = MainRunner.orderPage.languagesList.get(new Random().nextInt(MainRunner.orderPage.languagesList.size()));
        randCheckbox.click();
    }

    public void randTools_selection() {
        WebElement randCheckbox = MainRunner.orderPage.toolsList.get(new Random().nextInt(MainRunner.orderPage.toolsList.size()));
        randCheckbox.click();
    }

    public void randEducation_selection() {
        Select select = new Select(educationSelection);
        select.selectByIndex(MainRunner.randGenerator(1, 6));
    }
    public void enter_salary(String salary) {
        salarySelection.sendKeys(salary);
    }

    public void order_button() {
        orderButton.click();
    }

}
