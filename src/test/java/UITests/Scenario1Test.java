package UITests;

import UI.Pages.CreditPage;
import UI.Pages.HomePage;
import UI.Pages.NewLineOfCreditPage;
import UI.Utils.BaseTest;
import UI.Utils.ScenarioObj;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

import static UI.Pages.CreditPage.Type.DRAW;
import static UI.Pages.CreditPage.Type.PAYMENT;

public class Scenario1Test extends BaseTest {

    @Test
    protected void scenario1Test() {
        DecimalFormat df = new DecimalFormat();
        df.getMaximumFractionDigits();
        ScenarioObj scenarioObj = new ScenarioObj(ScenarioObj.ScenarioEnum.SCENARIO2);
        List<HashMap<String, String>> drawList = scenarioObj.getDraws();
        List<HashMap<String, String>> paymentList = scenarioObj.getPayments();
        String apr = scenarioObj.getApr();
        String limit = scenarioObj.getLimit();
        HomePage homePage = new HomePage();
        NewLineOfCreditPage newCreditPage = homePage.clickNewCredit();
        CreditPage creditPage = newCreditPage.fillOutApr(apr)
                .fillOutCredit(limit)
                .submit();
        drawList.forEach(d -> creditPage.fillOutTransaction(DRAW, d.get("amount"), d.get("day")));
        paymentList.forEach(d -> creditPage.fillOutTransaction(PAYMENT, d.get("amount"), d.get("day")));
        float interestResult = creditPage.getInterestOf30days();
        float interestFromTransactions = Float.valueOf(new DecimalFormat("##.##").format(creditPage.getInterestOfTransactions(apr)));
        float interestFromData = scenarioObj.getInterest();

        float totalFromData = scenarioObj.getTotal();
        float totalResult = creditPage.getTotalPayoffAt30days();

        float creditAvailableResult = creditPage.getCreditAvailable().get(0);
        float creditAvailableFromTransactions = creditPage.creditAvailableFromTransactions();

        boolean interestBoolean = (interestResult == interestFromTransactions) && (interestFromTransactions == interestFromData);
        boolean totalBoolean = (totalFromData == totalResult);
        boolean creditBoolean = (creditAvailableResult == creditAvailableFromTransactions);
        Assert.assertTrue(interestBoolean, "Diff interests between: " + interestResult + " " + interestFromTransactions + " " + interestFromData);
        Assert.assertTrue(totalBoolean, "Diff total between: " + totalFromData + " " + totalResult);
        Assert.assertTrue(creditBoolean, "Diff credit between: " + creditAvailableResult + " " + creditAvailableFromTransactions);
        System.out.println();
    }
}
