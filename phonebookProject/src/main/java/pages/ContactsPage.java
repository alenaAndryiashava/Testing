package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactsPage extends PageBase{

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='collapse navbar-collapse']//div//button[2]")
    WebElement logOutButton;

    @FindBy(xpath = "//li//a[@href='/']")
    WebElement contacts;

    @FindBy(xpath = "//select[@id='langSelect']")
    WebElement selectLang;

    @FindBy(xpath = "//a[@href='/contacts']")
    public WebElement addNewContactDialogOpenButton;

    @FindBy(xpath = "//*[@id=\"add-contact-modal\"]/a")
    public WebElement addNewContactDialogCloseButton;

    @FindBy(xpath = "//h4[@class='modal-title']")
    public List<WebElement> AddContactTextInDialog;

    @FindBy(className = "list-group-item.list-group-item-action")
    public List<WebElement> contactCards;

    @FindBy(xpath = "//button[@routerlink='/account']")
    WebElement accountButton;



//    @FindBy(xpath = "//option[contains(text(),'English')]")
//    WebElement engLang;

    public LoginPage logout(){
        click(logOutButton);
        return new LoginPage(driver);
    }

    public WebElement getContacts(String lang) {
        String contactWord = getContactWord(lang);
        contacts = driver.findElement(By.xpath("//a[contains(text(),'"+contactWord+"')]"));
        return contacts;
    }

    public static String getContactWord(String lang) {
        logger.info("Language specified: " + lang);
        String contactWord = null;
        if (lang.equals("English")) {
            contactWord = "Contacts";
        } else if (lang.equals("Russian")) {
            contactWord = "Контакты";
        } else if (lang.equals("German")) {
            contactWord = "Kontakte";
        } else if (lang.equals("Ukraine")) {
            contactWord = "Контакти";
        } else {
            logger.error("Language not supported: "+ lang);
            throw new RuntimeException("Language not supported: "+ lang);
        }
        return contactWord;
    }

    public ContactsPage selectLanguage(String lang){
        click(selectLang);
        WebElement language = driver.findElement(By.xpath("//option[contains(text(),'"+lang+"')]"));
        click(language);
        return this;
    }
    public ContactsPage openAddContactWindow() {
        click(addNewContactDialogOpenButton);
        return this;
    }

    public ContactsPage closeAddContactWindow() {
        click(addNewContactDialogCloseButton);
        return this;
    }
    public AccountPage openAccount() {
        click(accountButton);
        return new AccountPage(driver);
    }
    @FindBy(id = "form-name")
    WebElement fNameField;
    @FindBy(id = "form-lastName")
    WebElement lNameField;
    @FindBy(id = "form-about")
    WebElement aboutField;
    @FindBy(xpath = "//*[@id=\"add-contact-form\"]/div[4]/button[2]")
    WebElement saveBtn;
    public ContactsPage fillAddContactsForm(String firstNameKeys, String lastNameKeys, String aboutKeys){
        type(fNameField, firstNameKeys);
        type(lNameField, lastNameKeys);
        type(aboutField, aboutKeys);
        saveBtn.click();
        return new ContactsPage(driver);

    }

    public AccountPage clickOnContact(WebElement element) {
        element.click();
        return new AccountPage(driver);
    }
    //step 3 - new contact added -
    // Assert.assertEquals(dr.findElement(By.id("contact-first-name")).getText(), "firstNameExample");
    public void clickToContactMenu() {
        contacts.click();
    }
    @FindBy(xpath = "//input[@id='input-search-contact']")
    WebElement searchField;

    @FindBy(xpath = "//div[@class='d-flex justify-content-between']")
    WebElement firstSearchResult;
    public String getSearchContact(String fullName) {
        searchField.sendKeys(fullName);
        return firstSearchResult.getText();

    }

}


