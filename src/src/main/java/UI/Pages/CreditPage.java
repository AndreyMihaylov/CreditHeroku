package UI.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Optional;

import static UI.Utils.WebDriverFactory.getDriver;

public class CreditPage extends BasePage {

    @FindBy(xpath = "//strong[contains(text(),'Apr:')]//..")
    WebElement apr;

    @FindBy(xpath = "//strong[contains(text(),'Credit Available:')]//..")
    private
    WebElement creditAvailable;

    @FindBy(xpath = "//strong[contains(text(),'Credit Line Opened:')]//..")
    WebElement creditLineOpened;

    @FindBy(xpath = "//strong[contains(text(),'Interest at 30 days:')]//..")
    private
    WebElement interestOf30days;

    @FindBy(xpath = "//strong[contains(text(),'Total Payoff at 30 days:')]//..")
    private
    WebElement totalPayoffAt30days;

    @FindBy(xpath = "//select[@id='transaction_type']")
    private
    WebElement types;

    @FindBy(xpath = "//select[@id='transaction_applied_at']")
    WebElement day;

    @FindBy(xpath = "//input[@id='transaction_amount']")
    WebElement amountInput;

    @FindBy(xpath = "//input[@name='commit']']")
    WebElement saveTransactionButton;

    @FindBy(xpath = "//a[@href='/line_of_credits']")
    WebElement backToHomePage;

    public Float getApr() {
        return Float.valueOf(apr.getText().split(":")[1].replaceAll("%", "").trim());
    }

    public ArrayList<Float> getCreditAvailable() {
        String str = creditAvailable.getText().split(":")[1].trim();
        ArrayList<Float> listAvailableOf = new ArrayList<>();
        listAvailableOf.add(Float.valueOf(str.split("of")[0].replaceAll("[$, ]", "")));
        listAvailableOf.add(Float.valueOf(str.split("of")[1].replaceAll("[$, ]", "")));
        return listAvailableOf;
    }

    public String gerCreditLineOpened() {
        return apr.getText().split(":")[1].trim();
    }

    public Float getInterestOf30days() {
        return Float.valueOf(interestOf30days.getText().split(":")[1].replaceAll("[$,]", "").trim());
    }

    public Float getTotalPayoffAt30days() {
        return Float.valueOf(totalPayoffAt30days.getText().split(":")[1].replaceAll("[$,]", "").trim());
    }

    public void selectType(Type type){
        Select select = new Select(types);
        select.selectByValue(type.toString());
    }

    public HomePage goBack(){
        click(backToHomePage);
        return new HomePage();
    }

    enum Type {
        DRAW("Draw"), PAYMENT("Payment");

        private final String value;

        Type(String value) {
            this.value=value;
        }

        public String getValue() {
            return value;
        }
    }

}
