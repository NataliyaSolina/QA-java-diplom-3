package org.example.pom;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Property.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;

public class MainPage {
    private final WebDriver driver;
    private final By titleMainPage = By.xpath(".//main//h1[text() = 'Соберите бургер']");
    private final By buttonLogin = By.xpath(".//main//section[2]//button[text()='Войти в аккаунт']");
    private final By linkPersonalAccount = By.xpath(".//header//p[text()='Личный Кабинет']/parent::a[@href='/account']");
    private final By linkBun = By.xpath(".//main//section[1]//span[text()='Булки']/parent::div");
    private final By linkSauce = By.xpath(".//main//section[1]//span[text()='Соусы']/parent::div");
    private final By linkFilling = By.xpath(".//main//section[1]//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(titleMainPage));
    }

    public void checkForLoad() {
        String title = "Соберите бургер";
        assertEquals(title, driver.findElement(titleMainPage).getText());
        assertEquals(BASE_URL + PATH_MAIN, driver.getCurrentUrl());
    }

    public void clickButtonLogin() {
        driver.findElement(buttonLogin).click();
    }

    public void clickLinkPersonalAccount() {
        driver.findElement(linkPersonalAccount).click();
    }

    public void clickLinkBun() {
        driver.findElement(linkBun).click();
    }

    public void checkNotSelectedBun() {
        String selectClass = "type_current";
        MatcherAssert.assertThat(driver.findElement(linkBun).getAttribute("class"), not(containsString(selectClass)));
    }

    public void checkSelectedBun() {
        String selectClass = "type_current";
        MatcherAssert.assertThat(driver.findElement(linkBun).getAttribute("class"), containsString(selectClass));
    }

    public void clickLinkSauce() {
        driver.findElement(linkSauce).click();
    }

    public void checkNotSelectedSauce() {
        String selectClass = "type_current";
        MatcherAssert.assertThat(driver.findElement(linkSauce).getAttribute("class"), not(containsString(selectClass)));
    }

    public void checkSelectedSauce() {
        String selectClass = "type_current";
        MatcherAssert.assertThat(driver.findElement(linkSauce).getAttribute("class"), containsString(selectClass));
    }

    public void clickLinkFilling() {
        driver.findElement(linkFilling).click();
    }

    public void checkNotSelectedFilling() {
        String selectClass = "type_current";
        MatcherAssert.assertThat(driver.findElement(linkFilling).getAttribute("class"), not(containsString(selectClass)));
    }

    public void checkSelectedFilling() {
        String selectClass = "type_current";
        MatcherAssert.assertThat(driver.findElement(linkFilling).getAttribute("class"), containsString(selectClass));
    }
}
