package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.demoqa.ProgressBar;



public class ProgressBarTest extends TestBase {
    String url = "https://demoqa.com/progress-bar";
    String percent = Integer.toString(faker.number().numberBetween(10, 99));
    String actualPercent;
    ProgressBar progressBar;

    @BeforeMethod
    public void openUrl() {
        driver.get(url);
    }

    @Test
    public void progressBarTest() {
        progressBar = new ProgressBar(driver);
        progressBar.clickToButton();
        progressBar.clickToStop(percent);
        actualPercent = progressBar.getPercent();
        Assert.assertEquals(actualPercent, percent + "%");
        System.out.println(percent);
    }

}
