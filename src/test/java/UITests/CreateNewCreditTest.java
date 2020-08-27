package UITests;

import UI.Pages.HomePage;
import UI.Pages.NewLineOfCreditPage;
import UI.Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateNewCreditTest extends BaseTest {

    @Test
    @Parameters({"apr", "limit"})
    public void createNewCreditTest(@Optional("35.00") String apr, @Optional("1000") String limit) {
        HomePage homePage = new HomePage();
        int sizeOfCreditsBefore = homePage.getShowCredit().size();
        NewLineOfCreditPage creditPage = homePage.clickNewCredit();
        homePage = creditPage.fillOutApr(apr)
                .fillOutCredit(limit)
                .submit()
                .goBack();
        int sizeOfCreditsAfter = homePage.getShowCredit().size();
        Assert.assertTrue(sizeOfCreditsAfter - sizeOfCreditsBefore == 1);
    }
}
