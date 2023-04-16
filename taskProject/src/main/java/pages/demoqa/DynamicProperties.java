package pages.demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DynamicProperties {
    WebDriver driver;

    public DynamicProperties(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id=\"visibleAfter\"]")
    private WebElement waitedButton;
    @FindBy(xpath = "//button[@id=\"colorChange\"]")
    private WebElement colorChangeButton;
    public void waitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.visibilityOf(waitedButton));
    }
    public String getCss() {
        return colorChangeButton.getCssValue("color");
    }
}
