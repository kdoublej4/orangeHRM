package org.example.pages;

import org.example.drivers.DriverManager;
import org.example.helpers.BrowserHelper;
import org.example.helpers.MenuNavigate;
import org.example.helpers.WebElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    public WebDriver driver = DriverManager.getDriver();
    public WebElementActions webElementActions = new WebElementActions();
    public BrowserHelper browserHelper = new BrowserHelper(DriverManager.getDriver());
    public WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    public MenuNavigate menuNavigate = new MenuNavigate(driver, wait);
}
