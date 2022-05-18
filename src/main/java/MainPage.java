import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    public static final String MAIN_URL = "https://stellarburgers.nomoreparties.site/";


    @FindBy(how = How.CLASS_NAME, using = "button_button__33qZ0")
    private SelenideElement loginButton;

    public LoginPage clickLoginButton() {
        this.loginButton.click();
        return page(LoginPage.class);
    }

    @FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
    private SelenideElement personalAccountButton;

    public LoginPage clickPersonalAccountButtonBeforeLogin() {
        this.personalAccountButton.click();
        return page(LoginPage.class);
    }

    public AccountProfilePage clickPersonalAccountButtonAfterLogin() {
        this.personalAccountButton.click();
        return page(AccountProfilePage.class);
    }


    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__linkText__3q_va")
    private SelenideElement constructorButton;

    public boolean isConstructorButtonDisplayed() {
        return this.constructorButton.shouldBe(visible).isDisplayed();
    }

    @FindBy(how = How.XPATH, using = ".//span[text()='Булки']")
    private SelenideElement bunTab;

    public MainPage clickBunTab() {
        this.bunTab.click();
        return this;
    }

    @FindBy(how = How.XPATH, using = ".//h2[text()='Булки']")
    private SelenideElement bunTabTitle;

    public boolean isBunTabTitleDisplayed() {
        return this.bunTabTitle.isDisplayed();
    }

    @FindBy(how = How.XPATH, using = ".//span[text()='Соусы']")
    private SelenideElement sauceTab;

    public MainPage clickSauceTab() {
        this.sauceTab.click();
        return this;
    }

    @FindBy(how = How.XPATH, using = ".//h2[text()='Соусы']")
    private SelenideElement sauceTabTitle;

    public boolean isSauceTabTitleDisplayed() {
        return this.sauceTabTitle.isDisplayed();
    }

    @FindBy(how = How.XPATH, using = ".//span[text()='Начинки']")
    private SelenideElement fillingTab;

    public MainPage clickFillingTab() {
        this.fillingTab.click();
        return this;
    }

    @FindBy(how = How.XPATH, using = ".//h2[text()='Начинки']")
    private SelenideElement fillingTabTitle;

    public boolean isFillingTabTitleDisplayed() {
        return this.fillingTabTitle.isDisplayed();
    }
}