import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class ForgotPasswordPage {
    public static final String FORGOT_PASS_URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement loginButton;

    public LoginPage clickLoginButton() {
        this.loginButton.click();
        return page(LoginPage.class);
    }
}

