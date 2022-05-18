import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    String email = "tom@yandex.by";
    String password = "321321";

//    @After
//    public void logoutAccount() {
//        boolean isUserLogout = open(AccountProfilePage.ACC_PROFILE_URL, AccountProfilePage.class)
//                .clickLogoutLink()
//                .isConstructorButtonDisplayed();
//        assertTrue("Main page is not opened after logout", isUserLogout);
//    }

    @Test
    @DisplayName("Вход по кнопке \"Войти\" на главной странице")
    public void loginViaLoginButtonOnMainPage() {
        boolean isMainPageDisplayedAfterLogin = open(MainPage.MAIN_URL, MainPage.class)
                .clickLoginButton()
                .setEmailField(email)
                .setPasswordField(password)
                .clickLoginButton()
                .isConstructorButtonDisplayed();
        assertTrue("Main page is not opened after login", isMainPageDisplayedAfterLogin);

    }

    @Test
    @DisplayName("Вход по кнопке \"Личный кабинет\" на главной странице")
    public void loginViaPersonalAccountButtonOnMainPage() {
        boolean isMainPageDisplayedAfterLoginViaPersonalAccountButton = open(MainPage.MAIN_URL, MainPage.class)
                .clickPersonalAccountButtonBeforeLogin()
                .setEmailField(email)
                .setPasswordField(password)
                .clickLoginButton()
                .isConstructorButtonDisplayed();
        assertTrue("Main page is not opened after login", isMainPageDisplayedAfterLoginViaPersonalAccountButton);

    }

    @Test
    @DisplayName("Вход по кнопке \"Войти\" на странице регистрации")
    public void loginViaLoginButtonOnRegistrationPage() {
        boolean isMainPageDisplayedAfterLoginViaPersonalAccountButton = open(MainPage.MAIN_URL, MainPage.class)
                .clickLoginButton()
                .clickRegisterButton()
                .clickLoginButton()
                .setEmailField(email)
                .setPasswordField(password)
                .clickLoginButton()
                .isConstructorButtonDisplayed();
        assertTrue("Main page is not opened after login", isMainPageDisplayedAfterLoginViaPersonalAccountButton);

    }

    @Test
    @DisplayName("Вход по кнопке \"Войти\" на странице восстановления пароля")
    public void loginViaLoginButtonOnForgotPasswordPage() {
        boolean isMainPageDisplayedAfterLoginViaLoginButtonOnForgotPasswordPage = open(MainPage.MAIN_URL, MainPage.class)
                .clickLoginButton()
                .clickForgotPasswordButton()
                .clickLoginButton()
                .setEmailField(email)
                .setPasswordField(password)
                .clickLoginButton()
                .isConstructorButtonDisplayed();
        assertTrue("Main page is not opened after login", isMainPageDisplayedAfterLoginViaLoginButtonOnForgotPasswordPage);
    }
}
