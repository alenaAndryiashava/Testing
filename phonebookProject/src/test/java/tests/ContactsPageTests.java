package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AccountPage;
import pages.ContactsPage;
import pages.LoginPage;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ContactsPageTests extends TestBase{
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
/*    TODO ID*: tc_c1:
       Pre-conditions:* Logged in as test@gmail.com, Contacts page is opened
       Title:* New added contact is in the bottom of the Contacts list
       Steps:
       1. Click on Add new contact link
       2. Enter "Name +" current datetime as a first name, "Second name" + current datetime as a last name, enter current datetime as About.
       3. Press Save button
       4. Press on Contacts link
       5. Find line with contact data
       *******
       Bug report:
       Summary:
       New created contact didn't appear at the bottom of the Contacts list.
       *Pre-conditions:* Logged in as test@gmail.com, Contacts page is opened
       ********
       * * Steps to reproduce:
       1. Click on Add new contact link
       2. Enter "Name +" current datetime as a first name,
        "Second name" + current datetime as a last name,
        enter current datetime as About.
        3. Press Save button
        4. Press on Contacts link
        5. Find line with contact data


*
* Expected results:
3. New page is opened. Contact info, first name and last name fields contain data from Step2.
5. New contact is in the bottom of the list

*  Actual results:
5. New contact is in not at the bottom of the list

* Priority:
* Severity: Minor | 2
* Screenshot / attachment: screenshot. jpg
* Logs: .../logs.txt
* TC-id: tc_c1 / automated
* Component: webportal / Contacts list
* Product: Phonebook;
* Version: 8.9.102
* Product line:
* Environment:

 */
    @Test
    public void newContactAddedToTheBottomOfCl() {
        logger.info("Running test tc_c1: New added contact is in the bottom of the Contacts list");
        String language = "English";

        LoginPage myLoginPage = new LoginPage(driver);
        ContactsPage contactsPage = myLoginPage.login("test@gmail.com", "test@gmail.com");
        contactsPage.selectLanguage(language);

        contactsPage.openAddContactWindow();

        logger.info("Enter \"Name +\" current datetime as a first name, \"Second name\" + current datetime as a last name, enter current datetime as About.");
        String fName = "qa36_" + System.currentTimeMillis()/1000;
        String lName = "qa36_" + System.currentTimeMillis()/1000;
        String about = "About qa 36 " + System.currentTimeMillis()/1000;
        contactsPage.fillAddContactsForm(fName,lName,about);

        WebElement contacts = contactsPage.getContacts(language);
        contactsPage.click(contacts);
        sleep();

        List<WebElement> list =  driver.findElements(By.className("list-group"));

        int listContactsSize = list.size();
        System.out.println("Length of the list " + listContactsSize);

        Assert.assertTrue(list.get(listContactsSize-1).getText().contains(fName));
        Assert.assertTrue(list.get(listContactsSize-1).getText().contains(lName));


    }
    /* TODO:
        *ID*: tc_c2
        * Pre-conditions:* Logged in as test@gmail.com, Contacts page is opened, there are contacts in the list
        *
        * Title:* When contact is clicked in the Contact list, the Contact info page of this contact is opened
        *
        * Steps:
        1. Choose random contact from Contacts list, remember its name, and click on it
        2. Check that info in the contact list is the same as in the contact info
        *
        * Expected result:
        * 2. New page is opened -- Contact info, First name and last name fields contain data from the random contact

     */

    @Test(testName = "tc_c2")
    public void openAccountInfoPageOnClickOnContact() {
        logger.info("Running test tc_c2:");
        String language = "English";

        LoginPage myLoginPage = new LoginPage(driver);
        ContactsPage contactsPage = myLoginPage.login("test@gmail.com", "test@gmail.com");
        selectLanguage(language);
        sleep();

        List<WebElement> listGroup = driver.findElements(By.className("list-group"));
        int listContactsSize = listGroup.size();

        Assert.assertTrue(listContactsSize>0);
        logger.info("Number of contact buttons :" +String.valueOf(listContactsSize));

        int listIndex = ThreadLocalRandom.current().nextInt(0,listContactsSize - 1);
        WebElement contactButton = listGroup.get(listIndex);
        String buttonText = contactButton.getText();
        System.out.println(buttonText);
        AccountPage accountPage = contactsPage.clickOnContact(contactButton);
        sleep();

        Assert.assertEquals(accountPage.firstName.getText() +
                " " +
                accountPage.lastName.getText(), buttonText);

    }

}
