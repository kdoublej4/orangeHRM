package org.example.pages;

import lombok.SneakyThrows;
import org.example.drivers.DriverManager;
import org.example.entities.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.example.helpers.WebElementActions.wait;

public class WebTablesPage extends BasePage {
    @FindBy(xpath = "//*[@id='addNewRecordButton']")
    public WebElement addNewButton;
    @FindBy(xpath = "//*[@id='submit']")
    public WebElement submitButton;
    @FindBy(xpath = "//*[@id='firstName']")
    public WebElement inputFirstName;
    @FindBy(xpath = "//*[@id='lastName']")
    public WebElement inputLastName;
    @FindBy(xpath = "//*[@id='age']")
    public WebElement inputAge;
    @FindBy(xpath = "//*[@id='userEmail']")
    public WebElement inputEmail;
    @FindBy(xpath = "//*[@id='salary']")
    public WebElement inputSalary;
    @FindBy(xpath = "//*[@id='department']")
    public WebElement inputDepartment;

    //------------------------------------------------------------
    @SneakyThrows
    public WebTablesPage addNewEmployee(Employee employee) {
        List<Employee> employees = getEmployeesFromTable();
        boolean flag = false;
        for (Employee emp : employees) {
            if (emp.getEmail().equals(employee.getEmail())) {
                flag = true;
            }
        }
        if (!flag) {
            webElementActions.click(addNewButton)
                    .sendKeys(inputFirstName, employee.getFirstName())
                    .sendKeys(inputLastName, employee.getLastName())
                    .sendKeys(inputEmail, employee.getEmail())
                    .sendKeys(inputAge, String.valueOf(employee.getAge()))
                    .sendKeys(inputSalary, String.valueOf(employee.getSalary()))
                    .sendKeys(inputDepartment, employee.getDepartment())
                    .click(submitButton);
            Thread.sleep(3000);
            System.out.println("Employee with email: " + employee.getEmail() + " added");
        } else {
            System.err.println("An employee with this email: " + employee.getEmail() + " already exists");
        }
        return this;
    }

    @SneakyThrows
    public void removeEmployee(String email) {
        List<WebElement> rows = DriverManager.getDriver().findElements(By.cssSelector(".ReactTable .rt-tr-group"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector(".rt-td"));
            if (cells.get(3).getText().equals(email)) {
                WebElement deleteButton = row.findElement(By.cssSelector("[title='Delete']"));
                wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
                Thread.sleep(3000);
                System.out.println("Employee with email: " + email + " removed");
                break;
            }
            System.err.println("The employee you are looking for was not found");
        }
    }

    public List<Employee> getEmployeesFromTable() {
        List<WebElement> rows = DriverManager.getDriver().findElements(By.cssSelector(".ReactTable .rt-tr-group"));
        List<Employee> employees = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector(".rt-td"));
            String firstName = cells.get(0).getText().trim();
            String lastName = cells.get(1).getText().trim();
            String ageText = cells.get(2).getText().trim();
            String email = cells.get(3).getText().trim();
            String salaryText = cells.get(4).getText().trim();
            String department = cells.get(5).getText().trim();

            if (firstName.isEmpty() || lastName.isEmpty() || ageText.isEmpty() || email.isEmpty() || salaryText.isEmpty() || department.isEmpty()) {
                continue;
            }

            int age = Integer.parseInt(ageText);
            long salary = Long.parseLong(salaryText);

            employees.add(new Employee(firstName, lastName, age, email, salary, department));
        }
        return employees;
    }
    @SneakyThrows
    public void editEmployee(String email, String newData) {
        String[] array = newData.split("=");
        List<WebElement> rows = DriverManager.getDriver().findElements(By.cssSelector(".ReactTable .rt-tr-group"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.cssSelector(".rt-td"));

            if (cells.get(3).getText().equals(email)) {
                WebElement editButton = row.findElement(By.cssSelector("[title='Edit']"));
                wait.until(ExpectedConditions.elementToBeClickable(editButton)).click();
                WebElement requiredField = DriverManager.getDriver().findElement(findField(array[0]));
                webElementActions.clearTextField(requiredField).sendKeysWithEnter(requiredField ,array[1]);
            }
        }
        Thread.sleep(3000);
        System.out.println(array[0] + " field changed to " + array[1]);
    }

    public By findField(String fieldName) {
        String result = "";
        switch (fieldName) {
            case "firstname" -> result = "//*[@id='firstName']";
            case "lastname" -> result = "//*[@id='lastName']";
            case "email" -> result = "//*[@id='userEmail']";
            case "age" -> result = "//*[@id='age']";
            case "salary" -> result = "//*[@id='salary']";
            case "department" -> result = "//*[@id='department']";
            default -> throw new IllegalArgumentException("Invalid field name: " + fieldName);
        }
        return By.xpath(result);
    }
}
