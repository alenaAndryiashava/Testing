package pages.demoqa.windows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewTabAndWindowPage extends CommonWindowPage{
    public NewTabAndWindowPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//h1[@id=\"sampleHeading\"]")
    private WebElement newTabHeader;

    public boolean shouldBeVisible() {
        return newTabHeader.isDisplayed();
    }
}
