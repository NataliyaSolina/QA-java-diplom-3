import io.qameta.allure.Description;
import org.example.WebDriverRule;
import org.example.pom.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.example.Property.*;

public class LoginTest {
    @Rule
    public WebDriverRule browserRule = new WebDriverRule();
    private RegisterPage objRegisterPage;
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private FogotPassPage objFogotPassPage;

    @Before
    public void setUp() {
        objRegisterPage = new RegisterPage(browserRule.getDriver());
        objLoginPage = new LoginPage(browserRule.getDriver());
        objMainPage = new MainPage(browserRule.getDriver());
        objFogotPassPage = new FogotPassPage(browserRule.getDriver());

        browserRule.getDriver().get(BASE_URL + PATH_REGISTER);
        objRegisterPage.waitForLoad();

        objRegisterPage.register(NAME, EMAIL, PASSWORD);
    }

    @Test
    @Description("Вход по кнопке «Войти в аккаунт» на главной")
    public void checkLoginFromMainPageButtonLoginRezultOk() {
        browserRule.getDriver().get(BASE_URL + PATH_MAIN);
        objMainPage.waitForLoad();
        objMainPage.checkForLoad();

        objMainPage.clickButtonLogin();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();

        objLoginPage.login(EMAIL, PASSWORD);

        objMainPage.waitForLoad();
        objMainPage.checkForLoad();
    }

    @Test
    @Description("Вход через кнопку «Личный кабинет»")
    public void checkLoginFromMainPageLinkPersonalAccountRezultOk() {
        browserRule.getDriver().get(BASE_URL + PATH_MAIN);
        objMainPage.waitForLoad();
        objMainPage.checkForLoad();

        objMainPage.clickLinkPersonalAccount();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();

        objLoginPage.login(EMAIL, PASSWORD);

        objMainPage.waitForLoad();
        objMainPage.checkForLoad();
    }

    @Test
    @Description("Вход через кнопку в форме регистрации")
    public void checkLoginFromRegisterPageLinkLoginRezultOk() {
        browserRule.getDriver().get(BASE_URL + PATH_REGISTER);
        objRegisterPage.waitForLoad();
        objRegisterPage.checkForLoad();

        objRegisterPage.clickLinkLogin();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();

        objLoginPage.login(EMAIL, PASSWORD);

        objMainPage.waitForLoad();
        objMainPage.checkForLoad();
    }

    @Test
    @Description("Вход через кнопку в форме восстановления пароля")
    public void checkLoginFromFogotPassPageLinkLoginRezultOk() {
        browserRule.getDriver().get(BASE_URL + PATH_FOGOT_PASS);
        objFogotPassPage.waitForLoad();
        objFogotPassPage.checkForLoad();

        objFogotPassPage.clickLinkLogin();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();

        objLoginPage.login(EMAIL, PASSWORD);

        objMainPage.waitForLoad();
        objMainPage.checkForLoad();
    }
}
