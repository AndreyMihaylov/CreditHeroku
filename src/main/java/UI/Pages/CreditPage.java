package UI.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static UI.Utils.CommonUtils.addInfo;

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
        waitForVisability(creditAvailable);
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
        transactionLines.get(transactionLines.size() - 1).findElement(By.xpath("//td[last()]")).click();
        sleep(1);
        return this;
    }


    private CreditPage selectType(Type type) {
        Select select = new Select(types);
        select.selectByValue(type.value);
        addInfo("Selected "+ type.getValue() + " in dropdown: "+ types);
        return this;
    }

    private CreditPage fillOutAmount(String amount) {
        type(amountInput, amount);
        addInfo("Fill out amount: " + amount);

        return this;
    }

    private CreditPage appliedAtDay(String days) {
        int daysInt = Integer.valueOf(days);
        Select select = new Select(day);
        if (daysInt > 0 && daysInt < 31) {
            select.selectByValue(String.valueOf(daysInt));
            addInfo("Selected "+ days + " in dropdown: "+ day);

        } else {
            select.selectByValue("30");
            addInfo("Selected 30 in dropdown: "+ day +" because was providet wrong day: "+ days);

        }
        return this;
    }

    public CreditPage saveTransactionButton() {
        click(saveTransactionButton);
        sleep(1);
        return this;
    }

    public HomePage goBack() {
        sleep(1);
        click(backToHomePage);
        sleep(1);
        return new HomePage();
    }

    public CreditPage fillOutTransaction(Type type, String amount, String days) {
        addInfo("-------------START FILL OUT TRANSACTION");
        int sizeOfCreditLines = transactionLines.size();
        selectType(type)
                .fillOutAmount(amount)
                .appliedAtDay(days)
                .saveTransactionButton();
        while (transactionLines.size() - sizeOfCreditLines > 1) {
            clickRemoveLast();
        }
        addInfo("-------------FINISHED FILL OUT TRANSACTION");

        return this;
    }

    public float getInterestOfTransactions(String apr) {
        float interest = 0;
        for (int i = 0; i < transactionLines.size(); i++) {
            float amount = Float.valueOf(getAmountOfTransactions(i).replaceAll("[$,]", "").trim());
            if (getTypeOfTransactions(i).equals("Draw")) {
                interest = interest + ((31 - Integer.valueOf(getDayOfTransactions(i))) * amount);
            } else {
                interest = interest - ((31 - Integer.valueOf(getDayOfTransactions(i))) * amount);
            }

        }

        return interest*Float.valueOf(apr)/365/100;
    }

    private String getDayOfTransactions(int i) {
        return transactionLines.get(1).findElements(By.xpath("//td[1]")).get(i).getText();
    }

    private String getTypeOfTransactions(int i) {
        return transactionLines.get(1).findElements(By.xpath("//td[2]")).get(i).getText();
    }

    private String getAmountOfTransactions(int i) {
        return transactionLines.get(1).findElements(By.xpath("//td[3]")).get(i).getText();
    }

    public Float creditAvailableFromTransactions() {
        List<WebElement> list =transactionLines.get(1).findElements(By.xpath("//td[4]"));
        return Float.valueOf(list.get(list.size()-1).getText().replaceAll("[$,]", "").trim());
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
