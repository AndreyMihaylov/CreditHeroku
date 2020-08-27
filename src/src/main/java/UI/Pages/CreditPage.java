package UI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
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

    @FindBy(xpath = "//input[@name='commit']")
    WebElement saveTransactionButton;

    @FindBy(xpath = "//a[@href='/line_of_credits']")
    WebElement backToHomePage;

    @FindBy(xpath = "//table[@id='transactions_table']/tbody/tr")
    List<WebElement> transactionLines;

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

    public int getSizeOfTransactions() {
        return transactionLines.size();
    }

    public int getDays() {
        return transactionLines.size();
    }

    public int getTypes() {
        return transactionLines.size();
    }

    public int getAmounts() {
        return transactionLines.size();
    }

    public int getPrincipal() {
        return transactionLines.size();
    }

    public CreditPage clickRemove(int index) {
        transactionLines.get(index).findElement(By.xpath("//td[last()]")).click();
        return this;
    }

    public CreditPage clickRemoveLast() {
        transactionLines.get(transactionLines.size()-1).findElement(By.xpath("//td[last()]")).click();
        return this;
    }


    public CreditPage selectType(Type type) {
        Select select = new Select(types);
        select.selectByValue(type.value);
        return this;
    }

    public CreditPage fillOutAmount(String amount) {
        type(amountInput, amount);
        return this;
    }

    public CreditPage appliedAtDay(int days) {
        Select select = new Select(day);
        if (days > 0 && days < 31) {
            select.selectByValue(String.valueOf(days));
        } else {
            select.selectByValue("30");
        }
        return this;
    }

    public CreditPage saveTransactionButton() {
        click(saveTransactionButton);
        sleep(1);
        return this;
    }


    public HomePage goBack() {
        click(backToHomePage);
        sleep(1);
        return new HomePage();
    }

    public enum Type {
        DRAW("Draw"), PAYMENT("Payment");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }


}
