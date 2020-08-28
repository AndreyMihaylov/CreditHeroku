package UITests;

import UI.Pages.EditingLineOfCreditPage;
import UI.Pages.HomePage;
import UI.Utils.BaseTest;
import UI.Utils.EditDataObjNeg;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.text.DecimalFormat;
import java.util.List;

public class EditCreditTest extends BaseTest {

    @Test
    protected void editCreditTest() {
        HomePage homePage = new HomePage();
        int sizeOfCreditsBefore = homePage.getShowCredit().size();
        String aprChanged = String.valueOf(Float.valueOf(homePage.getLastApr()) + 1);
        String limitChanged = String.valueOf(Float.valueOf(homePage.getLastLimit()) + 1);
        EditingLineOfCreditPage editingLineOfCreditPage = homePage.clickEditLastCredit();
        editingLineOfCreditPage.fillOutApr(aprChanged)
                .fillOutCredit(limitChanged)
                .submit()
                .goBack();
        int sizeOfCreditsAfter = homePage.getShowCredit().size();
        String aprAfter = homePage.getLastApr();
        String limitAfter = homePage.getLastLimit();
        Assert.assertTrue(sizeOfCreditsAfter - sizeOfCreditsBefore == 0);
        Assert.assertTrue(aprChanged.equals(aprAfter) && limitChanged.equals(limitAfter));
    }

    @Test
    protected void editCreditTestZero() {
        HomePage homePage = new HomePage();
        int sizeOfCreditsBefore = homePage.getShowCredit().size();
        EditingLineOfCreditPage editingLineOfCreditPage = homePage.clickEditLastCredit();
        editingLineOfCreditPage.fillOutApr("0")
                .fillOutCredit("0")
                .submit()
                .goBack();
        int sizeOfCreditsAfter = homePage.getShowCredit().size();
        String aprAfter = homePage.getLastApr();
        String limitAfter = homePage.getLastLimit();
        Assert.assertTrue(sizeOfCreditsAfter - sizeOfCreditsBefore == 0);
        Assert.assertTrue(aprAfter.equals("0.0") && limitAfter.equals("0.0"));
    }

    @Test
    protected void editCreditTestLong() {
        HomePage homePage = new HomePage();
        int sizeOfCreditsBefore = homePage.getShowCredit().size();
        String aprChanged = "10.123456789";
        String limitChanged = "100.123456789";
        EditingLineOfCreditPage editingLineOfCreditPage = homePage.clickEditLastCredit();
        editingLineOfCreditPage.fillOutApr(aprChanged)
                .fillOutCredit(limitChanged)
                .submit()
                .goBack();
        int sizeOfCreditsAfter = homePage.getShowCredit().size();
        String aprAfter = homePage.getLastApr();
        String limitAfter = homePage.getLastLimit();
        Assert.assertTrue(sizeOfCreditsAfter - sizeOfCreditsBefore == 0);
        Assert.assertTrue(aprChanged.equals(aprAfter)
                && limitChanged.equals(limitAfter));
    }


    @Test

    protected void editCreditTestNegative() {
        SoftAssert softAssert = new SoftAssert();
        HomePage homePage = new HomePage();
        EditDataObjNeg editDataObjNeg = new EditDataObjNeg();
        List<String> aprs = editDataObjNeg.getApr();
        List<String> limits = editDataObjNeg.getLimit();
        EditingLineOfCreditPage editingLineOfCreditPage = homePage.clickEditLastCredit();
        for (String apr : aprs) {
            editingLineOfCreditPage.fillOutApr(apr).submit();
            softAssert.assertTrue(editingLineOfCreditPage.errorWindowIsDisplayed(), "Error window doesn't appear with apr: " + apr);
        }
        editingLineOfCreditPage.fillOutApr("35");
        for (String limit : limits) {
            editingLineOfCreditPage.fillOutCredit(limit).submit();
            softAssert.assertTrue(editingLineOfCreditPage.errorWindowIsDisplayed(), "Error window doesn't appear with limit: " + limit);
        }
        softAssert.assertAll();
    }
}
