package org.example;

import io.restassured.response.Response;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static java.util.Objects.isNull;
import static org.example.Property.*;

public class WebDriverRule extends ExternalResource {
    private WebDriver driver;
    ApiMethods apiMethod = new ApiMethods();
    Response response;
    public WebDriver getDriver() {
        return driver;
    }

    @Override
    protected void before() {
        String browser = System.getenv("browser");
        if (isNull(browser)) {
            browser = "yandex";
        }
        switch (browser) {
            case "fireFox":
                driver = new FirefoxDriver();
                break;
            case "yandex":
                System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
                driver = new ChromeDriver();
                break;
            case "chrome":
                driver = new ChromeDriver();
                break;
            default:
                throw new IllegalStateException("Не знаю такого");
        }

        response = apiMethod.authUserApi();
        if (response.statusCode() == 200) {
            apiMethod.deleteUserApi(response);
        }
    }

    @Override
    protected void after() {
        driver.quit();
        response = apiMethod.authUserApi();
        if (response.statusCode() == 200) {
            apiMethod.deleteUserApi(response);
        }
    }
}
