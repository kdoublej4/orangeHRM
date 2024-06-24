package org.example.helpers;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertHelper {
    private WebDriver driver;

    public AlertHelper(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для получения текущего alert окна
    public Alert getAlert() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    // Метод для принятия alert окна
    public void acceptAlert() {
        getAlert().accept();
    }

    // Метод для отклонения alert окна
    public void dismissAlert() {
        getAlert().dismiss();
    }

    // Метод для получения текста alert окна
    public String getAlertText() {
        return getAlert().getText();
    }

    // Метод для проверки наличия alert окна
    public boolean isAlertPresented() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    // Метод для принятия alert окна, если оно существует
    public void acceptAlertIfPresented() {
        if (!isAlertPresented()) {
            return;
        }
        acceptAlert();
    }

    // Метод для отклонения alert окна, если оно существует
    public void dismissAlertIfPresented() {
        if (!isAlertPresented()) {
            return;
        }
        dismissAlert();
    }

    // Метод для ввода текста в alert окно с типом prompt и принятия его
    public void acceptPrompt(String text) {
        if (!isAlertPresented()) {
            return;
        }
        Alert alert = getAlert();
        alert.sendKeys(text);
        alert.accept();
    }

    // Метод для ввода текста в alert окно с типом prompt и отклонения его
    public void dismissPrompt(String text) {
        if (!isAlertPresented()) {
            return;
        }
        Alert alert = getAlert();
        alert.sendKeys(text);
        alert.dismiss();
    }

    // Метод для принятия alert окна, если оно существует, с выводом сообщения в консоль
    public void acceptAlertWithMessage() {
        if (!isAlertPresented()) {
            System.out.println("No alert is presented.");
            return;
        }
        System.out.println("Alert message: " + getAlertText());
        acceptAlert();
    }

    // Метод для отклонения alert окна, если оно существует, с выводом сообщения в консоль
    public void dismissAlertWithMessage() {
        if (!isAlertPresented()) {
            System.out.println("No alert is presented.");
            return;
        }
        System.out.println("Alert message: " + getAlertText());
        dismissAlert();
    }
}
