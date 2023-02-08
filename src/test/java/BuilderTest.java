import io.qameta.allure.Description;
import org.example.pom.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.example.Property.*;

public class BuilderTest {
    private WebDriver driver;
    private MainPage objMainPage;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        driver = new ChromeDriver();
        objMainPage = new MainPage(driver);

        driver.get(BASE_URL + PATH_MAIN);
        objMainPage.waitForLoad();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    @Description("Проверь, что работают переходы к разделам «Соусы»")
    public void checkSwitchingByLinkSauceRezultOk() {
        objMainPage.checkNotSelectedSauce();
        objMainPage.clickLinkSauce();
        objMainPage.checkSelectedSauce();
    }

    @Test
    @Description("Проверь, что работают переходы к разделам «Булки»")
    public void checkSwitchingByLinkBunRezultOk() {
        objMainPage.clickLinkSauce();

        objMainPage.checkNotSelectedBun();
        objMainPage.clickLinkBun();
        objMainPage.checkSelectedBun();
    }

    @Test
    @Description("Проверь, что работают переходы к разделам «Начинки»")
    public void checkSwitchingByLinkFillingRezultOk() {
        objMainPage.checkNotSelectedFilling();
        objMainPage.clickLinkFilling();
        objMainPage.checkSelectedFilling();
    }

}
