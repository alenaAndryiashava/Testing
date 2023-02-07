package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends PageBase{

    public AccountPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@name='passwordInput']")
    WebElement passwordField;

    @FindBy(xpath = "//button[@class='btn btn-info my-4 btn-block']")
    WebElement acceptButton;

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void submitForm() {
        click(acceptButton);
    }

    @FindBy(id="contact-first-name")
    public WebElement firstName;

    @FindBy(id="contact-last-name")
    public WebElement lastName;


}
