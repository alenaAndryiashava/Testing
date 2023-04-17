package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.demoqa.SliderPage;

public class SliderTest extends TestBase{
    String url = "https://demoqa.com/slider";
    SliderPage sliderPage;

    @BeforeMethod
    public void openUrl() {
        driver.get(url);
    }

    @Test
    public void sliderTest() {
        sliderPage = new SliderPage(driver);
        sliderPage.moveSliderWithFor("37");
        Assert.assertEquals(sliderPage.getAttribute(), "37");
        Assert.assertEquals(sliderPage.getSliderValue(), "37");
    }
}
