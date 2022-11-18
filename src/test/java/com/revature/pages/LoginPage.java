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
    private WebElement usernameInput;


    //  PASSWORD INPUT
    @FindBy(id = "password")
    private WebElement passwordInput;

    //  LOGIN BUTTON
    @FindBy(id = "login")
    private WebElement loginButton;

    // CREATE ACCOUNT BUTTON
    @FindBy(xpath = "//p[@id='registerBtn']")
    private WebElement registerButton;

    // FUNCTIONALITY
    public void enter_username(String username) {
        usernameInput.sendKeys(username);
    }

    public void enter_password(String password) {
        passwordInput.sendKeys(password);
    }

    public void login_buttion() {
        loginButton.click();
    }

    public void register_button() {
        registerButton.click();
    }
}
