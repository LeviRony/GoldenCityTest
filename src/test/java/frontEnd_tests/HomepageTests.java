package frontEnd_tests;

import com.configurations.BaseTest;
import com.configurations.BaseUri;
import com.pageObjects.HomepageObjects;
import org.testng.Assert;
import org.testng.annotations.*;


public class HomepageTests extends BaseTest {


    private HomepageObjects homepage;

    @BeforeMethod
    public void setUpPageObjects() {
        page.navigate(BaseUri.goldenCityUrl());
        homepage = new HomepageObjects(page);
    }

    @Test
    public void testHeaderImageVisible() {
        Assert.assertTrue(homepage.isVisibleHeaderImage(),
                "Homepage header image should be visible");
    }

    @Test
    public void testClickFeaturedInvestmentLink1() {
        homepage.clickFeaturedInvestmentLink(1);
    }
}
