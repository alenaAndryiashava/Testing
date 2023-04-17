package pages.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TextBoxPage {
    WebDriver driver;

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='userName']")
    private WebElement nameInput;
    @FindBy(xpath = "//input[@id=\"userEmail\"]")
    private WebElement emailInput;
    @FindBy(xpath = "//textarea[@id=\"currentAddress\"]")
    private WebElement currentAddressInput;
    @FindBy(xpath = "//textarea[@id=\"permanentAddress\"]")
    private WebElement permanentAddressInput;
    @FindBy(xpath = "//button[@id=\"submit\"]")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id=\"output\"]//div")
    private WebElement infoField;

    public void fillTextBox(String name, String email, String currentAddress, String permanentAddress) {
        nameInput.clear();
        nameInput.sendKeys(name);
        emailInput.clear();
        emailInput.sendKeys(email);
        currentAddressInput.clear();
        currentAddressInput.sendKeys(currentAddress);
        permanentAddressInput.clear();
        permanentAddressInput.sendKeys(permanentAddress);
        saveButton.click();
    }

    public void waitText() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.textToBePresentInElement(infoField, "Name"));
    }

    public String getName() {
        return driver.findElement(By.xpath("//p[@id=\"name\"]")).getText();
    }

    public String getTextFromInfoField(String idName) {
        return driver.findElement(By.xpath(String.format("//p[@id='%s']", idName))).getText();
    }
}
