package org.example.helpers;

import org.example.utils.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.LinkedList;
import java.util.Set;

public class BrowserHelper {
    private WebDriver driver;

    public BrowserHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void openPageAndLogin() {
        driver.get(ConfigReader.getValue("baseUrl"));
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(ConfigReader.getValue("userName"));
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(ConfigReader.getValue("password"));
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    public void open(String url) {
        driver.navigate().to(url);
    }

    public void goForward() {
        driver.navigate().forward();
    }

    public void goBack() {
        driver.navigate().back();
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public void switchToWindow(int index) {
        LinkedList<String> windowsID = new LinkedList<>(getWindowHandles());

        if (index < 0 || index >= windowsID.size()) {
            throw new IllegalArgumentException("Invalid index: " + index);
        }
        driver.switchTo().window(windowsID.get(index));
    }

    public void switchToParentWindow() {
        LinkedList<String> windowsID = new LinkedList<>(getWindowHandles());
        driver.switchTo().window(windowsID.get(0));
    }

    public void switchToParentWithChildClose() throws InterruptedException {
        switchToParentWindow();
        LinkedList<String> windowsID = new LinkedList<>(getWindowHandles());

        if (windowsID.size() > 1) {
            for (int i = 1; i < windowsID.size(); i++) {
                driver.switchTo().window(windowsID.get(i));
                Thread.sleep(1000);
                driver.close();
            }
        }
        switchToParentWindow();
    }

//----------------------------------------------------------------------------------------------------------------------

    // Метод для открытия новой вкладки и перехода на указанный URL
    public void openNewTab(String url) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        switchToWindow(getWindowHandles().size() - 1);
        open(url);
    }

    // Метод для закрытия текущей вкладки и переключения на предыдущую вкладку
    public void closeCurrentTab() {
        LinkedList<String> windowsID = new LinkedList<>(getWindowHandles());
        driver.close(); // Закрывает текущую вкладку
        if (windowsID.size() > 1) {
            switchToWindow(windowsID.size() - 2);
        } else {
            switchToParentWindow();
        }
    }

    // Метод для переключения на фрейм по имени или ID
    public void switchToFrame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
    }

    // Метод для переключения на фрейм по индексу
    public void switchToFrame(int index) {
        driver.switchTo().frame(index);
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    // Метод для переключения на основной контент (вне фрейма)
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Метод для выполнения JavaScript кода
    public Object executeJavaScript(String script, Object... args) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script, args);
    }

    // Метод для установки таймаута загрузки страницы
    public void setPageLoadTimeout(long time, java.util.concurrent.TimeUnit unit) {
        driver.manage().timeouts().pageLoadTimeout(time, unit);
    }

    // Метод для установки таймаута на выполнение скрипта
    public void setScriptTimeout(long time, java.util.concurrent.TimeUnit unit) {
        driver.manage().timeouts().setScriptTimeout(time, unit);
    }

    // Метод для установки неявного ожидания
    public void setImplicitWait(long time, java.util.concurrent.TimeUnit unit) {
        driver.manage().timeouts().implicitlyWait(time, unit);
    }
}
