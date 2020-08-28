package UITests;

import org.testng.Assert;
import org.testng.annotations.Test;

import UI.Pages.HomePage;
import UI.Utils.BaseTest;

public class SmokeTest extends BaseTest {

    @Test
    protected void smokeShow(){
        HomePage homePage = new HomePage();
        homePage.clickShowLastCredit().goBack();
            Assert.assertTrue(homePage.getHomePageBody().isDisplayed());
    }

    @Test
    protected void smokeEdit(){
        HomePage homePage = new HomePage();
        homePage.clickEditLastCredit().goBack();
            Assert.assertTrue(homePage.getHomePageBody().isDisplayed());
    }

    @Test
    protected void smokeNewCreditLine(){
        HomePage homePage = new HomePage();
        homePage.clickNewCredit().goBack();
            Assert.assertTrue(homePage.getHomePageBody().isDisplayed());
    }
}
