import io.qameta.allure.Description;
import org.example.pom.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.example.Property.*;

public class LoginTest {
    private WebDriver driver;
    private RegisterPage objRegisterPage;
    private LoginPage objLoginPage;
    private MainPage objMainPage;
    private FogotPassPage objFogotPassPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        driver= new ChromeDriver();
        objRegisterPage = new RegisterPage(driver);
        objLoginPage = new LoginPage(driver);
        objMainPage = new MainPage(driver);
        objFogotPassPage = new FogotPassPage(driver);

        driver.get(BASE_URL + PATH_REGISTER);
        objRegisterPage.waitForLoad();

        objRegisterPage.setRegisterName(NAME);
        objRegisterPage.setRegisterEmail(EMAIL);
        objRegisterPage.setRegisterPassword(PASSWORD);
        objRegisterPage.clickButtonRegister();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("вход по кнопке «Войти в аккаунт» на главной")
    public void checkLoginFromMainPageButtonLoginRezultOk() {
        driver.get(BASE_URL + PATH_MAIN);
        objMainPage.waitForLoad();
        objMainPage.checkForLoad();

        objMainPage.clickButtonLogin();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();

        objLoginPage.setLoginEmail(EMAIL);
        objLoginPage.setLoginPassword(PASSWORD);
        objLoginPage.clickButtonLogin();

        objMainPage.waitForLoad();
        objMainPage.checkForLoad();
    }

    @Test
    @Description("вход через кнопку «Личный кабинет»")
    public void checkLoginFromMainPageLinkPersonalAccountRezultOk() {
        driver.get(BASE_URL + PATH_MAIN);
        objMainPage.waitForLoad();
        objMainPage.checkForLoad();

        objMainPage.clickLinkPersonalAccount();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();

        objLoginPage.setLoginEmail(EMAIL);
        objLoginPage.setLoginPassword(PASSWORD);
        objLoginPage.clickButtonLogin();

        objMainPage.waitForLoad();
        objMainPage.checkForLoad();
    }

    @Test
    @Description("вход через кнопку в форме регистрации")
    public void checkLoginFromRegisterPageLinkLoginRezultOk() {
        driver.get(BASE_URL + PATH_REGISTER);
        objRegisterPage.waitForLoad();
        objRegisterPage.checkForLoad();

        objRegisterPage.clickLinkLogin();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();

        objLoginPage.setLoginEmail(EMAIL);
        objLoginPage.setLoginPassword(PASSWORD);
        objLoginPage.clickButtonLogin();

        objMainPage.waitForLoad();
        objMainPage.checkForLoad();
    }

    @Test
    @Description("вход через кнопку в форме восстановления пароля")
    public void checkLoginFromFogotPassPageLinkLoginRezultOk() {
        driver.get(BASE_URL + PATH_FOGOT_PASS);
        objFogotPassPage.waitForLoad();
        objFogotPassPage.checkForLoad();

        objFogotPassPage.clickLinkLogin();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();

        objLoginPage.setLoginEmail(EMAIL);
        objLoginPage.setLoginPassword(PASSWORD);
        objLoginPage.clickButtonLogin();

        objMainPage.waitForLoad();
        objMainPage.checkForLoad();
    }
}
