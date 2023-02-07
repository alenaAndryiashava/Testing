package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.LoginPage;
import pages.SingleContactPage;

public class ContactsPageWithFakerTest extends TestBase{
    Faker faker = new Faker();
    ContactsPage cp;
    String firstName;
    String lastName;
    String fullName;
    String about;

    @BeforeMethod
    public void setUp(){
        LoginPage myLoginPage = new LoginPage(driver);
        cp = myLoginPage.login("test@gmail.com", "test@gmail.com");
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        about = faker.lorem().sentence(4);
        fullName = firstName + " " + lastName;

    }

    @Test(enabled = false)
    public void createContactChecking(){
        logger.info("Running test tc_c1: New added contact is in the bottom of the Contacts list");
        cp.openAddContactWindow();
        cp.fillAddContactsForm(firstName,lastName,about);
        SingleContactPage singleContactPage = new SingleContactPage(driver);
        sleep();
        Assert.assertEquals(singleContactPage.getFirstName(), firstName);
        Assert.assertEquals(singleContactPage.getLastName(), lastName);
        Assert.assertEquals(singleContactPage.getAbout(), about);

        cp.clickToContactMenu();
        sleep();

        String actualResult = cp.getSearchContact(fullName);
        Assert.assertEquals(actualResult, fullName);

    }
    // просто для примера
    @Test
    public void createContactCheckingTemp() {
        String fullNameWithoutSpace = firstName + lastName;
        logger.info("Running test tc_c1: New added contact is in the bottom of the Contacts list");
        cp.openAddContactWindow();
        cp.fillAddContactsForm(firstName, lastName, about);
        SingleContactPage singleContactPage = new SingleContactPage(driver);
        sleep();

        Assert.assertEquals(singleContactPage.getFirstName(), firstName);
        Assert.assertEquals(singleContactPage.getLastName(), lastName);
        Assert.assertEquals(singleContactPage.getAbout(), about);

        cp.clickToContactMenu();
        sleep();

        String actualResult = cp.getSearchContact(fullNameWithoutSpace);
        Assert.assertTrue(actualResult.contains(firstName));
        Assert.assertTrue(actualResult.contains(lastName));
    }


}
