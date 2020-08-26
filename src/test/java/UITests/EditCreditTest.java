package UITests;

import UI.Pages.HomePage;
import UI.Utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EditCreditTest extends BaseTest {

    @Test
    protected void createNewCreditTest() {
        final HomePage[] homePage = {new HomePage()};
        int sizeOfCreditsBefore = homePage[0].getShowCredit().size();
        String aprChanged = String.valueOf(Float.valueOf(homePage[0].getLastApr()) +1);
        String limitChanged = String.valueOf(Float.valueOf(homePage[0].getLastLimit())+1);
        homePage[0].clickEditLastCredit().ifPresent(
                p -> homePage[0] = p.fillOutApr(aprChanged)
                        .fillOutCredit(limitChanged)
                        .submit()
                        .goBack());
        int sizeOfCreditsAfter = homePage[0].getShowCredit().size();
        String aprAfter = homePage[0].getLastApr();
        String limitAfter = homePage[0].getLastLimit();
        Assert.assertTrue(sizeOfCreditsAfter - sizeOfCreditsBefore == 0);
        Assert.assertTrue(aprChanged.equals(aprAfter) && limitChanged.equals(limitAfter));
    }
}

