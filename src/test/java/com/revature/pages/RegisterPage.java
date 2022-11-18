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
    private WebElement fullNameInput;

    // USERNAME INPUT
    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameInput;

    // PASSWORD INPUT
    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    // PHONE NUMBER INPUT
    @FindBy(xpath = "//input[@id='phonenumber']")
    private WebElement phoneNumberInput;

    // EMAIL INPUT
    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailInput;

    // LOCATION INPUT
    @FindBy(xpath = "//input[@id='location']")
    private WebElement locationInput;

    // SIGN UP BUTTON
    @FindBy(xpath = "//button[contains(text(),'Sign Up')]")
    private WebElement signUpButton;

    // FUNCTIONALITY
    public void enter_full_name(String fullName) {
        fullNameInput.sendKeys(fullName);
    }

    public void enter_username(String username) {
        usernameInput.sendKeys(username);
    }

    public void enter_password(String password) {
        passwordInput.sendKeys(password);
    }

    public void enter_phone_number(String phoneNumber) {
        phoneNumberInput.sendKeys(phoneNumber);
    }

    public void enter_email(String email) {
        emailInput.sendKeys(email);
    }

    public void enter_location(String location) {
        locationInput.sendKeys(location);
    }

    public void sign_up_button() {
        signUpButton.click();
    }
}
