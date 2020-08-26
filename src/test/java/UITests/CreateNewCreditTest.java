package UITests;

import UI.Pages.HomePage;
import UI.Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CreateNewCreditTest extends BaseTest {

    @Test
    @Parameters({"apr","limit"})
    protected void  createNewCreditTest(@Optional("35.00" )String apr,@Optional("1000" ) String limit){
        final HomePage[] homePage = {new HomePage()};
        int sizeOfCreditsBefore = homePage[0].getShowCredit().size();
        homePage[0].clickNewCredit().ifPresent(
                p -> homePage[0] = p.fillOutApr(apr)
                                    .fillOutCredit(limit)
                                    .submit()
                                    .goBack());
        int sizeOfCreditsAfter = homePage[0].getShowCredit().size();
        Assert.assertTrue(sizeOfCreditsAfter-sizeOfCreditsBefore==1);
    }
}
