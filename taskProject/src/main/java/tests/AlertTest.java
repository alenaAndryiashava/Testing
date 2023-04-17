package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.demoqa.AlertPage;

public class AlertTest extends TestBase{
    String expectedAlertText = "This alert appeared after 5 seconds";
    String actualAlertText;
    String url = "https://demoqa.com/alerts";

    String text = faker.lorem().sentence(3);
    String actualText;

    String expectedText = "You entered " + text;

    AlertPage alertPage;


    @BeforeMethod
    public void openUrl() {
        driver.get(url);
    }


    @Test
    public void acceptAndTextAlertTest() {
        alertPage = new AlertPage(driver);
        alertPage.clickToTimerAlertButton();
        actualAlertText = alertPage.waitAlertAndClickToOk();
        Assert.assertEquals(actualAlertText, expectedAlertText);
    }

    @Test
    public void getResultText() {
        alertPage = new AlertPage(driver);
        alertPage.clickToPromtButton();
        alertPage.fillAlertInput(text);
        actualText = alertPage.getResultText();
        Assert.assertEquals(actualText, expectedText);
    }
}
