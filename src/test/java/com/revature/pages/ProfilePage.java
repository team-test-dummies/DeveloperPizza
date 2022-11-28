package com.revature.pages;

import com.revature.runner.MainRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage {
    @FindBy(xpath="//a[contains(text(),'Create Order')]")
    public WebElement createOrderButton;

    public ProfilePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // LOGOUT BUTTON
    @FindBy(xpath = "//a[@id='signout']")
    public WebElement logoutButton;

    // LOGOUT
    public void logout_button() {
        logoutButton.click();
    }

    // GET ACCOUNT DETAILS
    public boolean account_details() {
        boolean accountDetails = MainRunner.driver.findElement(By.xpath("//div[@id='accountInfo']")).isDisplayed();
        return accountDetails;
    }

    // GET ACCOUNT USERNAME
    @FindBy(xpath = "//div[1]//div[1]/ul//li[1]")
    public WebElement accountUsername;

    // GET ACCOUNT FULLNAME
    @FindBy(xpath = "//div[1]//div[1]/ul//li[2]")
    public WebElement accountFullname;

    // GET ACCOUNT PHONENUMBER
    @FindBy(xpath = "//div[1]//div[1]/ul//li[3]")
    public WebElement accountPhonenumber;

    // GET ACCOUNT EMAIL
    @FindBy(xpath = "//div[1]//div[1]/ul//li[4]")
    public WebElement accountEmail;

    // GET ACCOUNT Location
    @FindBy(xpath = "//div[1]//div[1]/ul//li[5]")
    public WebElement accountLocation;

    // GET ORDER LIST
    public boolean order_list() {
        return MainRunner.driver.findElement(By.xpath("//ul[@id='orders']")).isDisplayed();
    }

    // EDIT BUTTON
    @FindBy(xpath = "//ul[1]/li[1]/ul[1]/li[7]/button[1]")
    public WebElement editOrderButton;

    // EDIT ORDER BOX (POPUP)
    @FindBy(xpath = "//body/order-editor[1]/dialog[1]")
    public WebElement editOrderBox;

    // EDIT NAME
    @FindBy(xpath = "//label[2]/input[1]")
    public WebElement editName;

    // EDIT LANGUAGES
    @FindBy(xpath = "//label[3]//select[1]")
    public WebElement editLanguage;

    // EDIT TOOLS
    @FindBy(xpath = "//label[4]/select[1]")
    public WebElement editTool;

    // EDIT EDUCATION
    @FindBy(xpath = "//label[5]/select[1]")
    public WebElement editEducation;

    // EDIT SALARY
    @FindBy(xpath = "//label[6]/input[1]")
    public WebElement editSalary;

    // CONFIRM BUTTON
    @FindBy(xpath = "//button[contains(text(),'Confirm')]")
    public WebElement confirmOrderButton;

    // DELETE BUTTON
    @FindBy(xpath = "//ul[1]/li[1]/ul[1]/li[7]/button[2]")
    public WebElement deleteOrderButton;

    // GET ORDER NAME
    @FindBy(xpath = "//ul[1]/li[1]/ul[1]/li[2]/output[1]")
    public WebElement orderName;

    // GET ORDER LANGUAGE
    @FindBy(xpath = "//ul[1]/li[1]/ul[1]/li[3]/output[1]")
    public WebElement orderLanguage;

    // GET ORDER TOOL
    @FindBy(xpath = "//ul[1]/li[1]/ul[1]/li[4]/output[1]")
    public WebElement orderTool;

    // GET ORDER EDUCATION
    @FindBy(xpath = "//ul[1]/li[1]/ul[1]/li[5]/output[1]")
    public WebElement orderEducation;

    // GET ORDER SALARY
    @FindBy(xpath = "//ul[1]/li[1]/ul[1]/li[6]/output[1]")
    public WebElement orderSalary;

    @FindBy(xpath = "//div/div[@id='accountInfo']/ul/li[6]/button[contains(text(), 'Delete')]")
    public WebElement deleteProfileButton;

    @FindBy(xpath = "//dialog[@id='delete-dialog']")
    public WebElement deleteDialogBox;

    @FindBy(xpath = "//dialog/p")
    public WebElement dialogText;

    @FindBy(xpath = "//dialog//input[1]")
    public WebElement usernameDialogInput;

    @FindBy(xpath = "//dialog//input[2]")
    public WebElement passwordDialogInput;

    @FindBy(xpath = "//dialog/button[contains(text(), 'Delete')]")
    public WebElement dialogBoxDeleteBtn;

    @FindBy(xpath = "//dialog/button[contains(text(), 'Cancel')]")
    public WebElement dialogBoxCancelBtn;

    @FindBy(xpath = "//div/div[@id='accountInfo']/ul/li[6]/button[contains(text(), 'Edit')]")
    public WebElement editProfileButton;


}
