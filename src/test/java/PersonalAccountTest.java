import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;


public class PersonalAccountTest {

    String email = "tom@yandex.by";
    String password = "321321";

    @Test
    @DisplayName("Переход в личный кабинет")
    public void goToPersonalAccount() {
        boolean isPersonalAccountOpened = open(MainPage.MAIN_URL, MainPage.class)
                .clickLoginButton()
                .setEmailField(email)
                .setPasswordField(password)
                .clickLoginButton()
                .clickPersonalAccountButtonAfterLogin()
                .isProfileLinkDisplayed();
        assertTrue("Personal account page is not opened", isPersonalAccountOpened);
    }

    @Test
    @DisplayName("Выход из личного кабинета пользователя")
    public void logoutFromAccount() {


    }
}
