package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.LoginPage;

public class LoginPageTests extends TestBase{

    @Test
    public void loginPositiveTest(){
        logger.info("Starting login test");
        //String language = System.getProperty("language");
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
        logger.info("Starting open new contact window");
       ContactsPage contactsPage = new ContactsPage(driver);
       positiveLogin();
       contactsPage.openAddContactWindow();
       sleep();
        driver.findElement(By.xpath("//*[@id=\"add-contact-modal\"]/h4"));
        sleep();
        logger.info("Starting close new contact window");
        contactsPage.closeAddContactWindow();
        logger.info("Finished openAndCloseNewContactDialog test");

    }
}
