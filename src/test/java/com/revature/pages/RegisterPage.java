package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    public RegisterPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // FULL NAME INPUT
    @FindBy(xpath = "//input[@id='fullname']")
    public WebElement fullNameInput;

    // USERNAME INPUT
    @FindBy(xpath = "//input[@id='username']")
    public WebElement usernameInput;

    // PASSWORD INPUT
    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordInput;

    // PHONE NUMBER INPUT
    @FindBy(xpath = "//input[@id='phonenumber']")
    public WebElement phoneNumberInput;

    // EMAIL INPUT
    @FindBy(xpath = "//input[@id='email']")
    public WebElement emailInput;

    // LOCATION INPUT
    @FindBy(xpath = "//input[@id='location']")
    public WebElement locationInput;

    // SIGN UP BUTTON
    @FindBy(xpath = "//button[@id='signup']")
    public WebElement signupButton;

    @FindBy(xpath = "//div/div/div[2]//div/p[@id='registerErr']")
    public WebElement errorMessage;

    @FindBy(xpath = "//div//p/a[contains(text(), 'Click to Login')]")
    public WebElement alreadyRegistered;
}
