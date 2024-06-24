import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.example.enums.Endpoints.*;

public class test extends BaseTest {

    @BeforeClass
    public void beforeClass() {
        browserHelper.openPageAndLogin();
    }

    @Test
    public void test123() {
        menuNavigate.navigateTo(MY_INFO);
        webElementActions.pause(5);
    }
}
