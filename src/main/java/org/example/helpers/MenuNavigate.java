package org.example.helpers;
import org.example.enums.Endpoints;
import org.example.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.enums.Endpoints.*;

public class MenuNavigate {
    private WebDriver driver;
    private WebDriverWait wait;

    public MenuNavigate(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void navigateTo(Endpoints section) {
        String xpath = MAIN.getEndpoint() + section.getEndpoint();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
//        waitForElementToBeVisible(By.xpath(xpath));
        WebElement element = driver.findElement(By.xpath(xpath));

        if (section == MAINTENANCE) {
            element.click();
            driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ConfigReader.getValue("password"));
            driver.findElement(By.xpath("//button[@type='submit']")).click();
        } else {
            element.click();
        }
    }

//    private void waitForElementToBeVisible(By locator) {
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//    }
}
