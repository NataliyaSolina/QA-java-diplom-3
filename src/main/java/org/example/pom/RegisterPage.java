package org.example.pom;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Property.*;
import static org.junit.Assert.assertEquals;

public class RegisterPage {
    private final WebDriver driver;
    private final By titleRegisterPage = By.xpath(".//main//h2[text() = 'Регистрация']");
    private final By inputNameFormRegister = By.xpath(".//main//form[contains(@class, 'Auth_form')]//label[text()='Имя']/following-sibling::input");
    private final By inputEmailFormRegister = By.xpath(".//main//form[contains(@class, 'Auth_form')]//label[text()='Email']/following-sibling::input");
    private final By inputPasswordFormRegister = By.xpath(".//main//form[contains(@class, 'Auth_form')]//label[text()='Пароль']/following-sibling::input");
    private final By errorPasswordFormRegister = By.xpath(".//main//form[contains(@class, 'Auth_form')]//label[text()='Пароль']/parent::div/following-sibling::p[contains(@class, 'input__error')]");
    private final By buttonRegisterFormRegister = By.xpath(".//main//form[contains(@class, 'Auth_form')]//button[text()='Зарегистрироваться']");
    private final By linkLogin = By.xpath(".//main//a[text()='Войти' and @href='/login']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание загрузки страницы «Регистрация»")
    public void waitForLoad() {
        // подожди 8 секунд, пока появится веб-элемент с нужным текстом
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(titleRegisterPage));
    }

    @Step("Проверка страницы «Регистрация»")
    public void checkForLoad() {
        String title = "Регистрация";
        assertEquals(title, driver.findElement(titleRegisterPage).getText());
        assertEquals(BASE_URL + PATH_REGISTER, driver.getCurrentUrl());
    }

    @Step("Ввод имени в форме регистрации")
    public void setRegisterName(String name) {
        driver.findElement(inputNameFormRegister).sendKeys(name);
    }

    @Step("Ввод email в форме регистрации")
    public void setRegisterEmail(String email) {
        driver.findElement(inputEmailFormRegister).sendKeys(email);
    }

    @Step("Ввод пароля в форме регистрации")
    public void setRegisterPassword(String password) {
        driver.findElement(inputPasswordFormRegister).sendKeys(password);
    }

    @Step("Клик «Зарегистрироваться» в форме регистрации")
    public void clickButtonRegister() {
        driver.findElement(buttonRegisterFormRegister).click();
    }

    @Step("Регистрация")
    public void register(String name, String email, String password) {
        setRegisterName(name);
        setRegisterEmail(email);
        setRegisterPassword(password);
        clickButtonRegister();
    }

    @Step("Проверка ошибки в форме регистрации")
    public void checkShowErrorPassword() {
        String stringError = "Некорректный пароль";
        assertEquals(stringError, driver.findElement(errorPasswordFormRegister).getText());
    }

    @Step("Клик «Войти» на странице «Регистрация»")
    public void clickLinkLogin() {
        driver.findElement(linkLogin).click();
    }
}
