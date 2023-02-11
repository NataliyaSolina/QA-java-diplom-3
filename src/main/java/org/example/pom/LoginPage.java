package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Property.*;
import static org.junit.Assert.assertEquals;

public class LoginPage {
    private final WebDriver driver;
    private final By titleLoginPage = By.xpath(".//main//h2[text() = 'Вход']");
    private final By inputEmailFormLogin = By.xpath(".//main//form[contains(@class, 'Auth_form')]//label[text()='Email']/following-sibling::input");
    private final By inputPasswordFormLogin = By.xpath(".//main//form[contains(@class, 'Auth_form')]//label[text()='Пароль']/following-sibling::input");
    private final By buttonLoginFormLogin = By.xpath(".//main//form[contains(@class, 'Auth_form')]//button[text()='Войти']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание загрузки страницы «Вход»")
    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(titleLoginPage));
    }

    @Step("Проверка страницы «Вход»")
    public void checkForLoad() {
        String title = "Вход";
        assertEquals(title, driver.findElement(titleLoginPage).getText());
        assertEquals(BASE_URL + PATH_LOGIN, driver.getCurrentUrl());
    }

    @Step("Ввод email в форме авторизации")
    public void setLoginEmail(String email) {
        driver.findElement(inputEmailFormLogin).sendKeys(email);
    }

    @Step("Ввод пароля в форме авторизации")
    public void setLoginPassword(String password) {
        driver.findElement(inputPasswordFormLogin).sendKeys(password);
    }

    @Step("Клик «Войти» в форме авторизации")
    public void clickButtonLogin() {
        driver.findElement(buttonLoginFormLogin).click();
    }
}
