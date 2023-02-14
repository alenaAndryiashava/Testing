package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class ContactPage extends BasePage{
    public ContactPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@id='contact-first-name']")
    WebElement firstNameField;

    @FindBy(xpath = "//div[@id='contact-last-name']")
    WebElement lastNameField;

    @FindBy(xpath = "//div[@id='contact-description']")
    WebElement contactDescription;


    public String getName() {
        return firstNameField.getText();
    }


    public Map<String, String> getContactInfo(){
        Map<String, String> contactsInfo = new HashMap<>();
        contactsInfo.put("first name", firstNameField.getText());
        contactsInfo.put("last name", lastNameField.getText());
        contactsInfo.put("contact description", contactDescription.getText());

        return contactsInfo;
    }



}
