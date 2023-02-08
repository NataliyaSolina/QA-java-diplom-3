import io.qameta.allure.Description;
import org.example.pom.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.example.Property.*;

public class LogoutTest {
    private WebDriver driver;
    private LoginPage objLoginPage;
    private PersonalAccountPage objPersonalAccountPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        driver = new ChromeDriver();
        RegisterPage objRegisterPage = new RegisterPage(driver);
        objLoginPage = new LoginPage(driver);
        MainPage objMainPage = new MainPage(driver);
        objPersonalAccountPage = new PersonalAccountPage(driver);

        driver.get(BASE_URL + PATH_REGISTER);
        objRegisterPage.waitForLoad();

        objRegisterPage.setRegisterName(NAME);
        objRegisterPage.setRegisterEmail(EMAIL);
        objRegisterPage.setRegisterPassword(PASSWORD);
        objRegisterPage.clickButtonRegister();

        driver.get(BASE_URL + PATH_LOGIN);
        objLoginPage.waitForLoad();

        objLoginPage.setLoginEmail(EMAIL);
        objLoginPage.setLoginPassword(PASSWORD);
        objLoginPage.clickButtonLogin();

        objMainPage.waitForLoad();

        objMainPage.clickLinkPersonalAccount();

        objPersonalAccountPage.waitForLoad();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Выход из аккаунта по кнопке «Выйти» в личном кабинете")
    public void checkLogoutLinkLogoutRezultOk() {
        objPersonalAccountPage.ckickLinkLogout();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();
    }
}
