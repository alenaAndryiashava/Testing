package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase{

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "defaultRegisterFormEmail")
    public WebElement userName;

    @FindBy(xpath = "//*[@id=\"login-form\"]/div[2]/div[1]/div/input")
    public WebElement password;

    //@FindBy(xpath = "/html/body/app-root/app-login/div/div[2]/div/form/div[3]/div[1]/button")
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement loginButton;

    @FindBy(xpath = "//a[@routerlink='/user/registration']")
    public WebElement registerLink;

    @FindBy( id="email-error-required")
    public WebElement emailErrorMsg;

    @FindBy( id="password-error-required")
    public WebElement passwordErrorMsg;

    public ContactsPage login(String uName, String pass) {
        enterCreds(uName,pass);
        click(loginButton);
        return new ContactsPage(driver);
    }
    public void enterCreds(String uName, String pass) {
        type(userName, uName);
        type(password, pass);

    }

    public LoginPage loginNegative(String uName, String pass) {
        type(userName, uName);
        type(password, pass);
        click(loginButton);
        return this;
    }
    // Verify if the data in password field is visible as bullet signs.
    // assert true - input=password
}
