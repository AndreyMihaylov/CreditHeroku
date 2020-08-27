package UITests;

import UI.Pages.CreditPage;
import UI.Pages.HomePage;
import UI.Utils.BaseTest;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static UI.Pages.CreditPage.Type.DRAW;
import static UI.Pages.CreditPage.Type.PAYMENT;

public class DrawPaymentRemoveTest extends BaseTest {

    @Test
    @Parameters({"days", "amount"})
    protected void drawTest(@Optional("10") String days, @Optional("100") String amount) {
        HomePage homePage = new HomePage();
        CreditPage creditPage = homePage.clickShowLastCredit();
        ArrayList<Float> creditAvailable = creditPage.getCreditAvailable();
        int i = 0;
        while (creditAvailable.get(0) < creditAvailable.get(1) && i < 5) {
            int sizeBefore = creditPage.getSizeOfTransactions();
            if (creditPage != null) {
                creditPage.selectType(DRAW)
                        .appliedAtDay(Integer.valueOf(days))
                        .fillOutAmount(amount)
                        .saveTransactionButton();
                creditAvailable = creditPage.getCreditAvailable();
                i++;
            }
//            Assert.assertTrue(creditPage.getSizeOfTransactions()-sizeBefore==1);
        }
    }

    @Test
    @Parameters({"days", "amount"})
    protected void paymentTest(@Optional("10") String days, @Optional("100") String amount) {
        HomePage homePage = new HomePage();
        CreditPage creditPage = homePage.clickShowLastCredit();
        ArrayList<Float> creditAvailable = creditPage.getCreditAvailable();
        int i = 0;
        while (creditAvailable.get(0) >=0  && i < 5) {
            int sizeBefore = creditPage.getSizeOfTransactions();
            if (creditPage != null) {
                creditPage.selectType(PAYMENT)
                        .appliedAtDay(Integer.valueOf(days))
                        .fillOutAmount(amount)
                        .saveTransactionButton();
                creditAvailable = creditPage.getCreditAvailable();
                i++;
            }
            //           Assert.assertTrue(creditPage.getSizeOfTransactions()-sizeBefore==1);
        }
    }

    @Test
    protected void removeTest() {
        HomePage homePage = new HomePage();
        CreditPage creditPage = homePage.clickShowLastCredit();
        while(creditPage.getSizeOfTransactions()>0)  {
            int sizeBefore = creditPage.getSizeOfTransactions();
            if (creditPage != null) {
                creditPage.clickRemoveLast();
            }

            //           Assert.assertTrue(creditPage.getSizeOfTransactions()-sizeBefore==-1);
        }
    }
}
