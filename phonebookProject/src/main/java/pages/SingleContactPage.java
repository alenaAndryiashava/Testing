package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SingleContactPage extends PageBase{
    public SingleContactPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@id ='contact-first-name']")
    WebElement firstName;

    @FindBy(xpath = "//div[@id ='contact-last-name']")
    WebElement lastName;

    @FindBy(xpath = "//div[@id ='contact-description']")
    WebElement about;

    public String getFirstName(){
        return firstName.getText();
    }
    public String getLastName(){
        return lastName.getText();
    }
    public String getAbout(){
        return about.getText();
    }
}
