package UI.Pages;

import UI.Utils.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static UI.Utils.BaseTest.getWait;
import static UI.Utils.CommonUtils.addError;
import static UI.Utils.CommonUtils.addInfo;
import static UI.Utils.WebDriverFactory.getDriver;
import static org.openqa.selenium.support.PageFactory.initElements;


public abstract class BasePage{


    @FindBy(xpath = "//div[@id='error_explanation']")
    WebElement errorWindow;


    public void waitForVisability(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForClick(WebElement element) {
        getWait().until(ExpectedConditions.visibilityOfAllElements(element));
        getWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForSelected(WebElement element) {
        getWait().until(ExpectedConditions.elementToBeSelected(element));
    }

    public BasePage() {
        initElements(getDriver(), this);
        addInfo("Redirect to "+ this.getClass().getSimpleName());

    }

    public BasePage click(WebElement element) {

        try {
            addInfo("Click on " + "'"+element.getText().toUpperCase()+ "' on "+ this.getClass().getSimpleName());
            waitForClick(element);
            element.click();
        } catch (Exception e) {
            addError("Click doesn't work, try JS click");
            jsClick(element);

        }
        return this;
    }

    public BasePage jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].click();", element);
        return this;
    }

    public BasePage type(WebElement element, String text) {
        waitForVisability(element);
        element.clear();
        element.sendKeys(text);
        return this;
    }

    public static void sleep(int sec){
        try {
            Thread.sleep(sec*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean errorWindowIsDisplayed(){
        addInfo("Verify of error window is appeared");
        return errorWindow.isDisplayed();
    }
}
