package org.example.pom;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.example.Property.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

public class PersonalAccountPage {
    private final WebDriver driver;
    private final By titlePersonalAccountPage = By.xpath(".//main//nav//p[contains(@class, 'Account_text') and text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By linkBuilder = By.xpath(".//header//p[text()='Конструктор']/parent::a[@href='/']");
    private final By linkLogo = By.xpath(".//header//div[contains(@class, 'header__logo')]/a[@href='/']");
    private final By linkLogout = By.xpath(".//main//nav//button[text()='Выход']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfElementLocated(titlePersonalAccountPage));
    }

    public void checkForLoad() {
        String title = "свои персональные данные";
        MatcherAssert.assertThat(driver.findElement(titlePersonalAccountPage).getText(), containsString(title));
        assertEquals(BASE_URL + PATH_PERSONAL_ACCOUNT, driver.getCurrentUrl());
    }

    public void ckickLinkBuilder() {
        driver.findElement(linkBuilder).click();
    }

    public void ckickLinkLogo() {
        driver.findElement(linkLogo).click();
    }

    public void ckickLinkLogout() {
        driver.findElement(linkLogout).click();
    }
}
