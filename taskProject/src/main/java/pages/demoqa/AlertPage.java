package pages.demoqa;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertPage {
    WebDriver driver;
    public AlertPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@id=\"timerAlertButton\"]")
    private WebElement timerAlertButton;

    @FindBy(xpath = "//button[@id=\"promtButton\"]")
    private WebElement promtButton;
    @FindBy(xpath = "//span[@id=\"promptResult\"]")
    private WebElement promptResult;
    public void clickToTimerAlertButton(){
        timerAlertButton.click();

    }
    public void clickToPromtButton(){
        promtButton.click();

    }

    public String waitAlertAndClickToOk() {
        new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return alertText;
    }
    public void fillAlertInput(String text){
        new WebDriverWait(driver,  Duration.ofSeconds(8)).until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().accept();

    }
    public String getResultText(){
        return promptResult.getText();
    }
}

