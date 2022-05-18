import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    @Test
    @DisplayName("Переход к разделу \"Булки\"")
    public void selectBunTab() {
        boolean isBunTabSelected = open(MainPage.MAIN_URL, MainPage.class)
                .clickFillingTab()
                .clickBunTab()
                .isBunTabTitleDisplayed();
        assertTrue("Bun tab isn't selected", isBunTabSelected);
    }

    @Test
    @DisplayName("Переход к разделу \"Соусы\"")
    public void selectSauceTab() {
        boolean isSauceTabSelected = open(MainPage.MAIN_URL, MainPage.class)
                .clickSauceTab()
                .isSauceTabTitleDisplayed();
        assertTrue("Sauce tab isn't selected", isSauceTabSelected);
    }

    @Test
    @DisplayName("Переход к разделу \"Начинки\"")
    public void selectFillingTab() {
        boolean isFillingTabSelected = open(MainPage.MAIN_URL, MainPage.class)
                .clickFillingTab()
                .isFillingTabTitleDisplayed();
        assertTrue("Filling tab isn't selected", isFillingTabSelected);

    }

}
