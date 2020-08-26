package UI.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Optional;


public class HomePage extends BasePage {

    @FindBy(xpath = "//a[@href='/line_of_credits/new']")
    private
    WebElement newLineOfCredit;

    @FindBy(xpath = "//a[contains(@href,'/line_of_credits') and contains(text(),'Show')]")
    private
    List<WebElement> showCredit;

    @FindBy(xpath = "//a[contains(@href,'/line_of_credits') and contains(text(),'Edit')]")
    private
    List<WebElement> editCredit;

    @FindBy(xpath = "//tr//td[1]")
    private
    List<WebElement> apr;

    @FindBy(xpath = "//tr//td[2]")
    private
    List<WebElement> creditLimit;

    @FindBy(xpath = "//a[contains(@href,'/line_of_credits') and contains(text(),'Destroy')]")
    private
    WebElement destroyCredit;

    @FindBy(xpath = "//body[@class='line_of_credits index']")
    WebElement homePageBody;

    public WebElement getNewLineOfCredit() {
        return newLineOfCredit;
    }

    public List<WebElement> getCreditLimit() {
        return creditLimit;
    }

    public List<WebElement> getShowCredit() {
        return showCredit;
    }

    public CreditPage clickNewLineOfCredit() {
        click(newLineOfCredit);
        return new CreditPage();
    }

    public WebElement getHomePageBody() {
        return homePageBody;
    }

    public String getLastApr(int number){
        return apr.get(number).getText();
    }

    public String getLastApr(){
        return getLastApr(apr.size()-1);
    }

    public String getLimit(int number){
        return creditLimit.get(number).getText();
    }

    public String getLastLimit(){
        return getLimit(apr.size()-1);
    }

    public Optional<CreditPage> clickShowLastCredit() {

        int size = showCredit.size();
        if (size != 0) {
            click(showCredit.get(size - 1));
            return Optional.of(new CreditPage());
        }
        return Optional.ofNullable(null);
    }

    public Optional<EditingLineOfCreditPage> clickEditLastCredit() {

        int size = editCredit.size();
        if (size != 0) {
            click(editCredit.get(size - 1));
            return Optional.of(new EditingLineOfCreditPage());
        }
        return Optional.ofNullable(null);
    }

    public Optional<NewLineOfCreditPage> clickNewCredit() {
        if (newLineOfCredit.isDisplayed()) {
            click(newLineOfCredit);
            return Optional.of(new NewLineOfCreditPage());
        }
        return Optional.ofNullable(null);
    }


}
