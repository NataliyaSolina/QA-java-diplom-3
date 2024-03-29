import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.example.ApiMethods;
import org.example.WebDriverRule;
import org.example.pom.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.example.Property.*;
import static java.util.Objects.nonNull;

public class RegistrationTest {
    @Rule
    public WebDriverRule browserRule = new WebDriverRule();
    private RegisterPage objRegisterPage;
    private LoginPage objLoginPage;
    String pass;

    @Before
    public void setUp() {
        objRegisterPage = new RegisterPage(browserRule.getDriver());
        objLoginPage = new LoginPage(browserRule.getDriver());

        browserRule.getDriver().get(BASE_URL + PATH_REGISTER);
        objRegisterPage.waitForLoad();
    }

    @After
    public void cleanUp() {
        if (nonNull(pass)) {
            ApiMethods apiMethod = new ApiMethods();
            Response response = apiMethod.authUserApi(EMAIL, pass);
            if (response.statusCode() == 200) {
                apiMethod.deleteUserApi(response);

            }
        }
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
        pass = RandomStringUtils.randomAlphanumeric(3, 5);
        objRegisterPage.register(NAME, EMAIL, pass);

        objRegisterPage.checkShowErrorPassword();
    }
}
