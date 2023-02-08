package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPage extends BasePage {
    public MenuPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='collapse navbar-collapse']//div//button[2]")
    WebElement logOutButton;

    @FindBy(xpath = "//li//a[@href='/']")
    WebElement contacts;

    @FindBy(xpath = "//select[@id='langSelect']")
    WebElement selectLang;

    @FindBy(xpath = "//button[@routerlink='/account']")
    WebElement accountButton;

    public WebElement getAccountButton() {
        return accountButton;
    }
}
