package pages.demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.TestBase;

import java.time.Duration;

public class ProgressBar {
    WebDriver driver;

    public ProgressBar(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id=\"startStopButton\"]")
    private WebElement startStopButton;

    @FindBy(xpath = "//div[@role=\"progressbar\"]")
    private WebElement progressBar;

    private final String ATTRIBUTE_NAME = "aria-valuenow";

    public void clickToButton() {
        startStopButton.click();
    }

    public String getAttributeValue() {
        return progressBar.getAttribute(ATTRIBUTE_NAME);
    }

    public void explicitWaitForAttribute(String percent) {
        new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.attributeToBe(progressBar, ATTRIBUTE_NAME, percent));

    }

    public void fluentWaitAttribute(String percent) {
        new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofNanos(2000000))
                .until(ExpectedConditions.attributeToBe(progressBar, ATTRIBUTE_NAME, percent));
    }

    public void clickToStop(String percent) {
//        explicitWaitForAttribute(percent);
        fluentWaitAttribute(percent);
        clickToButton();
    }

    public String getPercent() {
        return progressBar.getText();
    }


}

