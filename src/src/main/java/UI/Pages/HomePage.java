package UI.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static UI.Utils.WebDriverFactory.getDriver;
import static org.openqa.selenium.support.PageFactory.initElements;

public class HomePage extends BasePage {

    @FindBy(id = "header-log-in")
    WebElement logIn;

    @FindBy(xpath = "//span[contains(text(),'Log Out')]")
    WebElement logOut;

    @FindBy(id = "auth-login-modal")
    WebElement loginWithEmail;

    @FindBy(xpath = "//input[@id='email-auth-modal']")
    WebElement inputEmail;

    @FindBy(xpath = "//input[@id='pwd-auth-modal']")
    WebElement inputPassword;

    @FindBy(xpath = "//div[contains(@class,'t-toggleContainer')]")
    WebElement extendCategories;

    @FindBy(xpath = "//div[contains(@class,'t-userProfile')]")
    WebElement profile;

    @FindBy(xpath = "//div[@id='hamburger-join']")
    WebElement join;

    public HomePage() {
        initElements(getDriver(), this);
    }




}
