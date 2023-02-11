package org.example;

import io.restassured.response.Response;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static io.restassured.RestAssured.given;
import static java.util.Objects.isNull;
import static org.example.Property.*;
import static org.example.SettingsRequest.getSpec;

public class WebDriverRule extends ExternalResource {
    private WebDriver driver;
    public WebDriver getDriver() {
        return driver;
    }
    String cred = String.format("{\"email\": \"%s\",\n\"password\": \"%s\"}", EMAIL, PASSWORD);

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

        Response responseAuth =
                given()
                        .spec(getSpec())
                        .and()
                        .body(cred)
                        .when()
                        .post("/api/auth/login");
        if (responseAuth.statusCode() == 200) {
            given()
                    .spec(getSpec())
                    .and()
                    .header("Authorization", responseAuth.path("accessToken"))
                    .when()
                    .delete("/api/auth/user");
        }
    }

    @Override
    protected void after() {
        driver.quit();
        Response responseAuth =
                given()
                        .spec(getSpec())
                        .and()
                        .body(cred)
                        .when()
                        .post("/api/auth/login");
        if (responseAuth.statusCode() == 200) {
            given()
                    .spec(getSpec())
                    .and()
                    .header("Authorization", responseAuth.path("accessToken"))
                    .when()
                    .delete("/api/auth/user");
        }
    }
}
