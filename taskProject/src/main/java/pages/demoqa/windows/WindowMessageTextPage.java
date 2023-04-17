package pages.demoqa.windows;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WindowMessageTextPage extends CommonWindowPage{
    public WindowMessageTextPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//body")
    private WebElement text;

    public String getText() {
        return text.getText();
    }
}
