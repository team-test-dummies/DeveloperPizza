package com.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    //  USERNAME INPUT
    @FindBy(id = "username")
    public WebElement usernameInput;

    //  PASSWORD INPUT
    @FindBy(id = "password")
    public WebElement passwordInput;

    //  LOGIN BUTTON
    @FindBy(id = "login")
    public WebElement loginButton;

    // CREATE ACCOUNT BUTTON
    @FindBy(xpath = "//p[@id='registerBtn']")
    public WebElement registerButton;

    // FUNCTIONALITY
    public void enter_username(String username) {
        usernameInput.sendKeys(username);
    }

    public void enter_password(String password) {
        passwordInput.sendKeys(password);
    }

    public void login_button() {
        loginButton.click();
    }

    public void register_button() {
        registerButton.click();
    }
}
