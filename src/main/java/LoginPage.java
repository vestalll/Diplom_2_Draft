import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {

    public static final String LOGIN_URL = "https://stellarburgers.nomoreparties.site/login";

    public String getLoginUrl() {
        return LOGIN_URL;
    }

    @FindBy(how = How.NAME, using = "name")
    private SelenideElement emailField;

    public LoginPage setEmailField(String email) {
        emailField.setValue(email);
        return this;
    }

    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement passwordField;

    public LoginPage setPasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }

    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;

    public MainPage clickLoginButton() {
        this.loginButton.click();
        return page(MainPage.class);
    }

    @FindBy(how = How.LINK_TEXT, using = "Зарегистрироваться")
    private SelenideElement registerButton;

    public RegistrationPage clickRegisterButton() {
        this.registerButton.click();
        return page(RegistrationPage.class);
    }

    @FindBy(how = How.LINK_TEXT, using = "Восстановить пароль")
    private SelenideElement forgotPasswordButton;

    public ForgotPasswordPage clickForgotPasswordButton() {
        this.forgotPasswordButton.click();
        return page(ForgotPasswordPage.class);
    }

}
