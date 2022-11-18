package com.revature.runner;

import com.revature.pages.LoginPage;
import com.revature.pages.MasterPage;
import com.revature.pages.OrderPage;
import com.revature.pages.RegisterPage;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Random;

@CucumberOptions(features = "classpath:features", glue = "com.revature.steps")
public class MainRunner extends AbstractTestNGCucumberTests {
    public static MasterPage masterPage;
    public static WebDriver driver;
    public static LoginPage loginPage;
    public static OrderPage orderPage;
    public static RegisterPage registerPage;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        orderPage = new OrderPage(driver);
        registerPage = new RegisterPage(driver);
        masterPage = new MasterPage(driver);
    }

    @AfterMethod
    public void cleanup() { driver.quit();}

    // USED FOR RANDOM SCENARIOS
    public static int randGenerator(int min, int max) {
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();
    }
}
