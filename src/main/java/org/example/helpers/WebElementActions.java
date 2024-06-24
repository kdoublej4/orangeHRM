package org.example.helpers;

import org.example.drivers.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WebElementActions {
    public static WebDriver driver = DriverManager.getDriver();
    public static WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
    public static Actions actions = new Actions(DriverManager.getDriver());

    public WebElementActions pressEnter(WebElement element) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        highlightElement(element);
        if (isElementInteractable(element)) {
            element.sendKeys(Keys.ENTER);
        }
        return this;
    }

    public WebElementActions click(WebElement element) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        highlightElement(element);
        element.click();
        return this;
    }

    public WebElementActions doubleClick(WebElement element) {
        waitElementToBeClickable(element);
        scrollToElement(element);
        if (isElementInteractable(element)) {
            highlightElement(element);
            actions.doubleClick(element).perform();
        }
        return this;
    }

    public WebElementActions rightClick(WebElement element) {
        waitElementToBeClickable(element);
        scrollToElement(element);
        if (isElementInteractable(element)) {
            highlightElement(element);
            actions.contextClick(element).perform();
        }
        return this;
    }

    public WebElementActions iteratyCLick(List<WebElement> elementList, int num) {
        List<Integer> randomValues = new Random().ints(0, elementList.size())
                .distinct()
                .limit(num)
                .boxed()
                .toList();

        randomValues.forEach(index -> click(elementList.get(index)));
        return this;
    }
    //----------------------------------------------------------------------------------------------------------------------
//          SendKeys commands
//----------------------------------------------------------------------------------------------------------------------
    public WebElementActions sendKeys(WebElement element, String value) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        highlightElement(element);
        if (isElementInteractable(element)) {
            element.sendKeys(value);
        }
        return this;
    }

    public WebElementActions sendKeysWithEnter(WebElement element, String value) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        highlightElement(element);
        if (isElementInteractable(element)) {
            try {
                element.sendKeys(Keys.CONTROL + "a");
                element.sendKeys(Keys.DELETE);
                element.sendKeys(value);
                element.sendKeys(Keys.ENTER);
            } catch (ElementNotInteractableException e) {
                System.err.println("Element not interactable");
                waitElementToBeVisible(element);
                scrollToElement(element);
                element.click();
                element.sendKeys(value);
                element.sendKeys(Keys.ENTER);
            }
        }
        return this;
    }

    public WebElementActions iteratySendKeysWithEnter(WebElement element, int num) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        highlightElement(element);

        List<String> subjects = new ArrayList<>() {{add("Maths"); add("Biology"); add("Chemistry"); add("Physics"); add("Arts"); add("History");}};
        List<String> tempList = new ArrayList<>();

        while (tempList.size() < num) {
            String subject = subjects.get(new Random().nextInt(subjects.size()));
            if (!tempList.contains(subject)) {
                tempList.add(subject);
            }
        }

        if (isElementInteractable(element)) {
            for (String subject : tempList) {
                try {
                    element.sendKeys(subject);
                    element.sendKeys(Keys.ENTER);
                } catch (ElementNotInteractableException e) {
                    System.err.println("Element not interactable");
                    waitElementToBeVisible(element);
                    scrollToElement(element);
                    element.click();
                    element.sendKeys(subject);
                    element.sendKeys(Keys.ENTER);
                }
            }
        }
        return this;
    }
    //----------------------------------------------------------------------------------------------------------------------
//          Extraction commands
//----------------------------------------------------------------------------------------------------------------------
    public WebElementActions extractOptions(WebElement element, String optionList) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        highlightElement(element);
        element.click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.xpath(optionList)));
        WebElement randomOption = options.get(new Random().nextInt(options.size()));
        randomOption.click();
        return this;
    }

    public List<WebElement> extractWebElementsFromString(String xpath) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
    }

    public List<WebElement> extractWebElementsFromWebElement(WebElement element) {
        Select select = new Select(element);
        return select.getOptions();
    }

    public WebElementActions collectAndSelectElement(String xpath) {
        List<WebElement> userRoles = driver.findElements(By.xpath(xpath));
        click(userRoles.get(new Random().nextInt(2) + 1));
        return this;
    }

    public WebElementActions sendKeysAutoComplete(WebElement element, String letter) {
        sendKeys(element, letter);
        pause(3);
        List<WebElement> list = driver.findElements(By.xpath("//div[@role='listbox']/div"));
        WebElement hiddenListBox = list.get(new Random().nextInt(list.size()));
        hiddenListBox.click();
        return this;
    }
    //----------------------------------------------------------------------------------------------------------------------
//          Conversion сommands
//----------------------------------------------------------------------------------------------------------------------
    public List<String> conversionFromWebElementsToStrings(List<WebElement> WebList) {
        return WebList.stream().map(WebElement::getText).toList();
    }

    //----------------------------------------------------------------------------------------------------------------------
//          Accessibility сommands
//----------------------------------------------------------------------------------------------------------------------
    public WebElementActions waitElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return this;
    }

    public WebElementActions waitElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return this;
    }

    public WebElementActions scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].scrollIntoView(true)", element);
        return this;
    }

    private boolean isElementInteractable(WebElement element) {
        try {
            return element.isDisplayed() && element.isEnabled();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.err.println("Element is not interactable: " + e.getMessage());
            return false;
        }
    }
    //----------------------------------------------------------------------------------------------------------------------
//          Other commands
//----------------------------------------------------------------------------------------------------------------------
    public WebElementActions clearTextField(WebElement element) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        if (isElementInteractable(element)) {
            element.clear();
        }
        return this;
    }

    public WebElementActions uploadFile(WebElement element, String filePath) {
        waitElementToBeVisible(element);
        scrollToElement(element);
        if (isElementInteractable(element)) {
            try {
                element.click();
            } catch (Exception e) {
                System.err.println("Unable to click the element: " + e.getMessage());
            }
            element.sendKeys(filePath);
        }
        return this;
    }

    public WebElementActions moveToElement(WebElement element) {
        waitElementToBeVisible(element);
        actions.moveToElement(element).perform();
        return this;
    }

    public void pause(Integer seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //----------------------------------------------------------------------------------------------------------------------
//              JavaScript commands
//----------------------------------------------------------------------------------------------------------------------
    public WebElementActions jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", element);
        return this;
    }

    public WebElementActions highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].style.border='3px solid red'", element);
        return this;
    }

    public WebElementActions jsSendKeys(WebElement element, String text) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].value=argumetns[1];", element, text);
        return this;
    }
}
