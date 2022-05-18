import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class AccountProfilePage {
    public static final String ACC_PROFILE_URL = "https://stellarburgers.nomoreparties.site/account/profile";


    @FindBy(how = How.CLASS_NAME, using = "Account_link__2ETsJ")
    private SelenideElement profileLink;

    public AccountProfilePage clickProfileLink() {
        this.profileLink.click();
        return this;
    }

    public boolean isProfileLinkDisplayed() {
        return this.profileLink.isDisplayed();
    }

    @FindBy(how = How.CLASS_NAME, using = "Account_button__14Yp3")
    private SelenideElement logoutLink;

    public MainPage clickLogoutLink() {
        this.logoutLink.click();
        return page(MainPage.class);
    }

}
