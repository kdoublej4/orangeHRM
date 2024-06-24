
import org.example.drivers.DriverManager;
import org.example.helpers.AlertHelper;
import org.example.helpers.BrowserHelper;
import org.example.helpers.MenuNavigate;
import org.example.helpers.WebElementActions;
import org.example.pages.Admin.userManagement.addUser.AddUserPage;
import org.example.utils.Randomizer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected WebElementActions webElementActions;
    protected Randomizer randomizer;
    protected AlertHelper alertHelper;
    protected BrowserHelper browserHelper;
    public MenuNavigate menuNavigate;
//------------------------------------------------------------------------------
    public AddUserPage addUserPage;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = DriverManager.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webElementActions = new WebElementActions();
        alertHelper = new AlertHelper(driver);
        browserHelper = new BrowserHelper(driver);
        randomizer = new Randomizer();
        menuNavigate = new MenuNavigate(driver, wait);
//------------------------------------------------------------------------------
        addUserPage = new AddUserPage();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        DriverManager.closeDriver();
    }
}
