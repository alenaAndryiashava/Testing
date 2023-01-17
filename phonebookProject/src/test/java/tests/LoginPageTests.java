package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.LoginPage;

public class LoginPageTests extends TestBase{

    @Test
    public void loginPositiveTest(){
        logger.info("Starting login test");
        String language = "English";
        LoginPage myLoginPage = new LoginPage(driver);
        ContactsPage contactsPage = myLoginPage.login("test@gmail.com", "test@gmail.com");
        sleep();
        contactsPage.selectLanguage(language);
        sleep();
        Assert.assertEquals(contactsPage.getContacts(language).getText(), ContactsPage.getContactWord(language));
        logger.info("Finished login test");
    }
    @Test
    public void openAndCloseNewContactDialog() {
        logger.info("Starting method login");
        ContactsPage contactsPage = new ContactsPage(driver);
        positiveLogin();
        selectLanguage("English");
        sleep();
       logger.info("Opening new contact dialog");
       contactsPage.openAddContactWindow();
       sleep();
        Assert.assertTrue(searchInPageSource("Contact description"));
        logger.info("Closing new contact dialog");
        contactsPage.closeAddContactWindow();
        sleep();
        logger.info("Finished openAndCloseNewContactDialog test");

    }
}
