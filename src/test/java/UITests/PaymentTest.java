package UITests;

import UI.Pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PaymentTest {

    @Test
    protected void smokeShow(){
        HomePage homePage = new HomePage();
        homePage.clickShowLastCredit().goBack();
            Assert.assertTrue(homePage.getHomePageBody().isDisplayed());
    }
}
