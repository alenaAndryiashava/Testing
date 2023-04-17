package pages.demoqa.windows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BrowserWindowsPage extends CommonWindowPage{
    public BrowserWindowsPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//button[@id=\"tabButton\"]")
    private WebElement newTabButton;

    @FindBy(xpath = "//div[@class=\"main-header\"]")
    private WebElement mainHeader;

    @FindBy(xpath = "//button[@id=\"windowButton\"]")
    private WebElement newWindowButton;

    @FindBy(xpath = "//button[@id=\"messageWindowButton\"]")
    private WebElement newMessageWindowButton;


    public void clickToNewTabButton() {
        newTabButton.click();
    }

    public void clickToNewWindowButton() {
        newWindowButton.click();
    }

    public void clickToNewMessageWindowButton() {
        newMessageWindowButton.click();
    }

    public boolean shouldBeVisible() {
        return mainHeader.isDisplayed();
    }
}
