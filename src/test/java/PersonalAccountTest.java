import io.qameta.allure.Description;
import org.example.WebDriverRule;
import org.example.pom.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.example.Property.*;

public class PersonalAccountTest {
    @Rule
    public WebDriverRule browserRule = new WebDriverRule();
    private MainPage objMainPage;
    private PersonalAccountPage objPersonalAccountPage;

    @Before
    public void setUp() {
        RegisterPage objRegisterPage = new RegisterPage(browserRule.getDriver());
        LoginPage objLoginPage = new LoginPage(browserRule.getDriver());
        objMainPage = new MainPage(browserRule.getDriver());
        objPersonalAccountPage = new PersonalAccountPage(browserRule.getDriver());

        browserRule.getDriver().get(BASE_URL + PATH_REGISTER);
        objRegisterPage.waitForLoad();

        objRegisterPage.register(NAME, EMAIL, PASSWORD);

        browserRule.getDriver().get(BASE_URL + PATH_LOGIN);
        objLoginPage.waitForLoad();

        objLoginPage.login(EMAIL, PASSWORD);

        objMainPage.waitForLoad();
    }

    @Test
    @Description("Переход в личный кабинет по клику на «Личный кабинет»")
    public void checkPersonalAccountInRezultOk() {
        objMainPage.clickLinkPersonalAccount();

        objPersonalAccountPage.waitForLoad();
        objPersonalAccountPage.checkForLoad();

    }
}
