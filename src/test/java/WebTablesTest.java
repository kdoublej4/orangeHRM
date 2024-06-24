import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.WebTablesPage;
import org.example.utils.ConfigReader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebTablesTest extends BaseTest {
    WebTablesPage webTablesPage = new WebTablesPage();

    @BeforeClass
    public void beforeClass() {
//        browserHelper.open(ConfigReader.getValue("baseURL") + WEBTABLES.getEndpoint());
        webElementActions.pause(3);
    }

    @Test(groups = {"Smoke", "E2E"}, description = "ololo")
    public void addEmployeeTest() {
        webTablesPage.editEmployee("alden@example.com", "firstname=John");
        webTablesPage.editEmployee("alden@example.com", "lastname=Doe");
    }
}
