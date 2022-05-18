
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {
    public static final String REG_URL = "https://stellarburgers.nomoreparties.site/register";

    @FindBy(how = How.XPATH, using = "//fieldset[1]//input")
    private SelenideElement nameField;

    public RegistrationPage setNameField(String name) {
        nameField.setValue(name);
        return this;
    }

    @FindBy(how = How.XPATH, using = "//fieldset[2]//input")
    private SelenideElement emailField;

    public RegistrationPage setEmailField(String email) {
        emailField.setValue(email);
        return this;
    }

    @FindBy(how = How.XPATH, using = "//input[@type='password']")
    private SelenideElement passwordField;

    public RegistrationPage setPasswordField(String password) {
        passwordField.setValue(password);
        return this;
    }

    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement registrationButton;

    public LoginPage clickRegistrationButtonWithValidDataInForm() {
        this.registrationButton.click();
        return page(LoginPage.class);
    }

    public RegistrationPage clickRegistrationButtonWithInvalidDataInForm() {
        this.registrationButton.click();
        return page(RegistrationPage.class);
    }

    @FindBy(how = How.CLASS_NAME, using = "input__error")
    private SelenideElement incorrectPasswordMessage;

    public boolean isIncorrectPasswordMessageDisplayed() {
        return this.incorrectPasswordMessage.isDisplayed();
    }

    @FindBy(how = How.CLASS_NAME, using = "Auth_link__1fOlj")
    private SelenideElement loginButton;

    public LoginPage clickLoginButton() {
        this.loginButton.click();
        return page(LoginPage.class);
    }

}
