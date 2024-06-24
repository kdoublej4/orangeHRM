package org.example.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class SafariWebDriver {
    public static WebDriver loadSafariDriver() {
        WebDriverManager.safaridriver().setup();
        WebDriver driver = new SafariDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }
}
