import io.qameta.allure.Description;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.WebDriverRule;
import org.example.pom.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.example.Property.*;

public class RegistrationTest {
    @Rule
    public WebDriverRule browserRule = new WebDriverRule();

    private RegisterPage objRegisterPage;
    private LoginPage objLoginPage;

    @Before
    public void setUp() {
        objRegisterPage = new RegisterPage(browserRule.getDriver());
        objLoginPage = new LoginPage(browserRule.getDriver());

        browserRule.getDriver().get(BASE_URL + PATH_REGISTER);
        objRegisterPage.waitForLoad();
    }

    @Test
    @Description("Успешная регистрация")
    public void checkRegistrationValidDataRezultOk() {
        objRegisterPage.register(NAME, EMAIL, PASSWORD);

        objLoginPage.waitForLoad();
        objLoginPage.checkForLoad();
    }

    @Test
    @Description("Ошибка для некорректного пароля")
    public void checkRegistrationInvalidPasswordRezultError() {
        objRegisterPage.register(NAME, EMAIL, RandomStringUtils.randomAlphabetic(3, 5));

        objRegisterPage.checkShowErrorPassword();
    }
}
