package org.example.drivers;

import org.example.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (ConfigReader.getValue("browser").toLowerCase()) {
                case "chrome":
                    driver = ChromeWebDriver.loadChromeDriver();
                    break;
                case "firefox":
                    driver = FirefoxWebDriver.loadFirefoxDriver();
                    break;
                case "safari":
                    driver = SafariWebDriver.loadSafariDriver();
                    break;
                case "edge":
                    driver = EdgeWebDriver.loadEdgeDriver();
                default:
                    throw new IllegalArgumentException("Incorrect WebDriver name");
            }
        }
        return driver;
    }

    public static void closeDriver() {
        try {
            if (driver != null) {
                driver.close();
                driver.quit();
                driver = null;
            }
        } catch (Exception e) {
            System.err.println("Error while closing driver: " + e.getMessage());
        }
    }

    public static void restartDriver() {
        closeDriver();
        getDriver();
    }

    public static String getCurrentUrl() {
        if (driver != null) {
            return driver.getCurrentUrl();
        }
        throw new IllegalStateException("Driver is not initialized. Call getDriver() first.");
    }

    public static String getTitle() {
        if (driver != null) {
            return driver.getTitle();
        }
        throw new IllegalStateException("Driver is not initialized. Call getDriver() first.");
    }
}
