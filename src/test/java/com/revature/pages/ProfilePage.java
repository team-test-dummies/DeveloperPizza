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

    // GET ACCOUNT DETAILS
    public boolean account_details() {
        boolean accountDetails = MainRunner.driver.findElement(By.xpath("//div[1]/ul[1]/li[1]")).isDisplayed();
        return accountDetails;
    }

    public boolean order_history() {
        boolean orderHistory = MainRunner.driver.findElement(By.xpath("//div[2]/ul[1]/li[1]")).isDisplayed();
        return orderHistory;
    }
}
