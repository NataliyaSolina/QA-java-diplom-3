import io.qameta.allure.Description;
import org.example.WebDriverRule;
import org.example.pom.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.example.Property.*;

public class BuilderInTest {
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

        objMainPage.clickLinkPersonalAccount();

        objPersonalAccountPage.waitForLoad();
    }

    @Test
    @Description("Переход из личного кабинета в конструктор по клику на «Конструктор»")
    public void checkMainPageLinkBuilderRezultOk() {
        objPersonalAccountPage.ckickLinkBuilder();

        objMainPage.waitForLoad();
        objMainPage.checkForLoad();
    }

    @Test
    @Description("Переход из личного кабинета в конструктор по клику на логотип Stellar Burgers")
    public void checkMainPageLogoRezultOk() {
        objPersonalAccountPage.ckickLinkLogo();

        objMainPage.waitForLoad();
        objMainPage.checkForLoad();
    }
}
