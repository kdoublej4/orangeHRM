package org.example.pages.Admin.userManagement.addUser;
import org.example.pages.Admin.entities.AddUserEntities;
import org.example.pages.Admin.enums.TopBarSection;
import org.example.pages.BasePage;
import org.example.utils.Randomizer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

public class AddUserPage extends BasePage {
    public String listBox = "//div[@role='listbox']/div";
    @FindBy(xpath = "//button[normalize-space()='Add']")
    public WebElement addButton;

    @FindBy(xpath = "//label[contains(text(), 'User Role')]/../following-sibling::div/div/div/div[contains(text(), '-- Select --')]")
    public WebElement userRole;

    @FindBy(xpath = "//label[contains(text(), 'Status')]/../following-sibling::div/div/div/div[contains(text(), '-- Select --')]")
    public WebElement status;

    @FindBy(xpath = "//input[@placeholder='Type for hints...']")
    public WebElement employeeNameInput;

    @FindBy(xpath = "//label[contains(text(), 'Username')]/../following-sibling::div/input")
    public WebElement userNameInput;

    @FindBy(xpath = "//label[starts-with(text(), 'Password')]/../following-sibling::div/input")
    public WebElement passwordInput;

    @FindBy(xpath = "//label[starts-with(text(), 'Confirm Password')]/../following-sibling::div/input")
    public WebElement confirmPasswordInput;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement saveButton;

    @FindBy(xpath = "//button[normalize-space()='Cancel']")
    public WebElement cancelButton;

    public String addNewUserAndSave(AddUserEntities addUserEntities) {
        webElementActions.click(driver.findElement(By.xpath(String.valueOf(TopBarSection.USER_MANAGAMENT.getTopBarSection()))))
                .click(driver.findElement(By.xpath(String.valueOf(TopBarSection.USERS.getTopBarSection()))))
                .click(addButton).click(userRole)
                .collectAndSelectElement(listBox)
                .click(status)
                .collectAndSelectElement(listBox)
                .sendKeysAutoComplete(employeeNameInput, addUserEntities.getEmployeeName())
                .sendKeys(userNameInput, addUserEntities.getUserName())
                .sendKeys(passwordInput, addUserEntities.getPassword())
                .sendKeys(confirmPasswordInput, addUserEntities.getPassword())
                .click(saveButton);

        return driver.findElement(By.xpath("//div[contains(text(),'" + addUserEntities.getUserName() + "')]")).getText();
    }

    public String addNewUserAndCancel(AddUserEntities addUserEntities) {
        webElementActions.click(driver.findElement(By.xpath(String.valueOf(TopBarSection.USER_MANAGAMENT.getTopBarSection()))))
                .click(driver.findElement(By.xpath(String.valueOf(TopBarSection.USERS.getTopBarSection()))))
                .click(addButton).click(userRole)
                .collectAndSelectElement(listBox)
                .click(status)
                .collectAndSelectElement(listBox)
                .sendKeysAutoComplete(employeeNameInput, addUserEntities.getEmployeeName())
                .sendKeys(userNameInput, addUserEntities.getUserName())
                .sendKeys(passwordInput, addUserEntities.getPassword())
                .sendKeys(confirmPasswordInput, addUserEntities.getPassword())
                .click(cancelButton);

        String result = null;
        try {
            driver.findElement(By.xpath("//div[contains(text(),'" + addUserEntities.getUserName() + "')]")).getText();
        } catch (Exception e) {
            result = "User not added";
        }
        return result;
    }
}
