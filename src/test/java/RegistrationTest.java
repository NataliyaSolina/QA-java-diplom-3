import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.pom.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.example.Property.*;

public class RegistrationTest {
    private WebDriver driver;
    private RegisterPage objRegisterPage;
    private LoginPage objLoginPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        driver = new ChromeDriver();
        objRegisterPage = new RegisterPage(driver);
        objLoginPage = new LoginPage(driver);

        driver.get(BASE_URL + PATH_REGISTER);
        objRegisterPage.waitForLoad();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Успешная регистрация")
    public void checkRegistrationValidDataRezultOk() {
        objRegisterPage.setRegisterName(NAME);
        objRegisterPage.setRegisterEmail(EMAIL);
        objRegisterPage.setRegisterPassword(PASSWORD);
        objRegisterPage.clickButtonRegister();

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();
    }

    @Test
    @Description("Ошибка для некорректного пароля")
    public void checkRegistrationInvalidPasswordRezultError() {
        objRegisterPage.setRegisterName(NAME);
        objRegisterPage.setRegisterEmail(EMAIL);
        objRegisterPage.setRegisterPassword(RandomStringUtils.randomAlphabetic(3, 5));
        objRegisterPage.clickButtonRegister();

        objRegisterPage.checkShowErrorPassword();
    }
}
