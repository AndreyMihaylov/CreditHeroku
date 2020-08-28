package UI.Pages;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static UI.Utils.CommonUtils.addInfo;

public class EditingLineOfCreditPage extends BasePage implements Credit {

    @FindBy(id = "line_of_credit_apr")
    WebElement aprInput;

    @FindBy(id = "line_of_credit_credit_limit")
    WebElement creditLimit;

    @FindBy(xpath = "//input[@name='commit']")
    WebElement submitButton;

    @FindBy(xpath = "//a[@href='/line_of_credits']")
    WebElement backToHomePage;

    public HomePage goBack(){
        click(backToHomePage);
        return new HomePage();
    }

    @Override
    public EditingLineOfCreditPage fillOutApr(String apr) {
        addInfo("Fill out apr: " + apr);
        type(aprInput,apr);
        return this;
    }

    @Override
    public EditingLineOfCreditPage fillOutCredit(String limit) {
        addInfo("Fill out limit: " + limit);
        type(creditLimit,limit);
        return this;
    }

    @Override
    public CreditPage submit() {
        click(submitButton);
        return new CreditPage();
    }
}
