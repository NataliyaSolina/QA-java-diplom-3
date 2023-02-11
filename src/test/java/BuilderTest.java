import io.qameta.allure.Description;
import org.example.WebDriverRule;
import org.example.pom.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.example.Property.*;

public class BuilderTest {
    @Rule
    public WebDriverRule browserRule = new WebDriverRule();
    private MainPage objMainPage;

    @Before
    public void setUp() {
        objMainPage = new MainPage(browserRule.getDriver());

        browserRule.getDriver().get(BASE_URL + PATH_MAIN);
        objMainPage.waitForLoad();
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
