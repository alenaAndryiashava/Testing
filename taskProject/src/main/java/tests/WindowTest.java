package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.demoqa.windows.BrowserWindowsPage;
import pages.demoqa.windows.NewTabAndWindowPage;
import pages.demoqa.windows.WindowMessageTextPage;

public class WindowTest extends TestBase{
    String url = "https://demoqa.com/browser-windows";
    BrowserWindowsPage browserWindowsPage;
    NewTabAndWindowPage newTabAndWindowPage;
    WindowMessageTextPage windowMessageTextPage;
    String originalWindow;

    String messageText = "Knowledge increases by sharing but not by saving. " +
            "Please share this website with your friends and in your organization.";

    @BeforeMethod
    public void openUrl() {
        driver.get(url);
    }

    @Test
    public void newTabTest() {
        browserWindowsPage = new BrowserWindowsPage(driver);
        originalWindow = browserWindowsPage.getOriginalWindow();
        browserWindowsPage.clickToNewTabButton();
        browserWindowsPage.switchToNewTab(originalWindow);
        newTabAndWindowPage = new NewTabAndWindowPage(driver);
        Assert.assertTrue(newTabAndWindowPage.shouldBeVisible());
        newTabAndWindowPage.closeAndSwitchToOriginalWindow(originalWindow);
        Assert.assertTrue(browserWindowsPage.shouldBeVisible());
    }

    @Test
    public void newWindowTest() {
        browserWindowsPage = new BrowserWindowsPage(driver);
        originalWindow = browserWindowsPage.getOriginalWindow();
        browserWindowsPage.clickToNewWindowButton();
        browserWindowsPage.switchToNewTab(originalWindow);
        newTabAndWindowPage = new NewTabAndWindowPage(driver);
        Assert.assertTrue(newTabAndWindowPage.shouldBeVisible());
        newTabAndWindowPage.closeAndSwitchToOriginalWindow(originalWindow);
        Assert.assertTrue(browserWindowsPage.shouldBeVisible());
    }

    @Test
    public void newMessageWindowTest() {
        browserWindowsPage = new BrowserWindowsPage(driver);
        originalWindow = browserWindowsPage.getOriginalWindow();
        browserWindowsPage.clickToNewMessageWindowButton();
        browserWindowsPage.switchToNewTab(originalWindow);
        windowMessageTextPage = new WindowMessageTextPage(driver);
        String text = windowMessageTextPage.getText();
        Assert.assertEquals(text, messageText);
        windowMessageTextPage.closeAndSwitchToOriginalWindow(originalWindow);
        Assert.assertTrue(browserWindowsPage.shouldBeVisible());
    }
    //TODO этот тест зависает, нужно разобраться почему
}
