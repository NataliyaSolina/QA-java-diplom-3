import io.qameta.allure.Description;
import org.example.WebDriverRule;
import org.example.pom.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.example.Property.*;

public class LogoutTest {
    @Rule
    public WebDriverRule browserRule = new WebDriverRule();
    private LoginPage objLoginPage;
    private PersonalAccountPage objPersonalAccountPage;

    @Before
    public void setUp() {
        RegisterPage objRegisterPage = new RegisterPage(browserRule.getDriver());
        objLoginPage = new LoginPage(browserRule.getDriver());
        MainPage objMainPage = new MainPage(browserRule.getDriver());
        objPersonalAccountPage = new PersonalAccountPage(browserRule.getDriver());

        browserRule.getDriver().get(BASE_URL + PATH_REGISTER);
        objRegisterPage.waitForLoad();

        objRegisterPage.register(NAME, EMAIL, PASSWORD);

        browserRule.getDriver().get(BASE_URL + PATH_LOGIN);
        objLoginPage.waitForLoad();

        objLoginPage.login(EMAIL, PASSWORD);

        objMainPage.waitForLoad();

        objMainPage.clickLinkPersonalAccount();

        objPersonalAccountPage.waitForLoad();
    }

    @Test
    @Description("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void checkLogoutLinkLogoutRezultOk() {
        objPersonalAccountPage.ckickLinkLogout();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();
    }
}
