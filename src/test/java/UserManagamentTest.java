
import io.qameta.allure.testng.Tag;
import org.example.listener.ScreenshotListener;
import org.example.pages.Admin.entities.AddUserEntities;
import org.example.utils.Randomizer;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static org.example.enums.Endpoints.*;
@Listeners(org.example.listener.ScreenshotListener.class)
public class UserManagamentTest extends BaseTest {
    @BeforeClass
    public void beforeClass() {
        browserHelper.openPageAndLogin();
    }

    @Test(groups = "Smoke")
    public void addNewUser() {
        AddUserEntities addUserEntities = Randomizer.addUserData();
        menuNavigate.navigateTo(ADMIN);
        Assert.assertEquals(addUserEntities.getUserName(), addUserPage.addNewUserAndSave(addUserEntities));
    }

    @Test(priority = 1, groups = "Smoke")
    public void addNewUserAndCancel() {
        AddUserEntities addUserEntities = Randomizer.addUserData();
        menuNavigate.navigateTo(ADMIN);
        Assert.assertEquals("User not added", addUserPage.addNewUserAndCancel(addUserEntities));
    }
}
