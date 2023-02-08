package org.example.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Property.*;
import static org.junit.Assert.assertEquals;

public class FogotPassPage {
    private final WebDriver driver;
    private final By titleLoginPage = By.xpath(".//main//h2[text() = 'Восстановление пароля']");
    private final By linkLogin = By.xpath(".//main//a[text()='Войти' and @href='/login']");

    public FogotPassPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(titleLoginPage));
    }

    public void checkForLoad() {
        String title = "Восстановление пароля";
        assertEquals(title, driver.findElement(titleLoginPage).getText());
        assertEquals(BASE_URL + PATH_FOGOT_PASS, driver.getCurrentUrl());
    }

    public void clickLinkLogin() {
        driver.findElement(linkLogin).click();
    }
}
