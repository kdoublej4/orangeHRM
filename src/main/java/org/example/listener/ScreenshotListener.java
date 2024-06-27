package org.example.listener;

import io.qameta.allure.Attachment;
import org.example.drivers.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ScreenshotListener implements ITestListener {
    WebDriver driver = DriverManager.getDriver();
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test " + result.getName() + " started.");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test " + result.getName() + " passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        if (driver != null){
            saveScreenshotPNG();
        }
        System.out.println("Test " + result.getName() + " failed.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test " + result.getName() + " skipped.");
//        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("Test " + result.getName() + " failed but is within the success percentage.");
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Starting execution of test context: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Finished execution of test context: " + context.getName());
    }
}
