package tests;

import com.github.javafaker.Faker;
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

    /*   TODO:
           *ID*: tc_l2
           * *Pre-conditions:* Logged out, login page opened
           * * Title:* Verify a warning message when email and password field are left empty after editing
           * * *Steps:*
           * 1. left click on email field
           * 2. left click on free space beyond fields
           * 3. verify if warning message is displayed (email is required)
           * * 4. left click on password field
           * * 5. left click on free space beyond fields
           * * 6. verify if warning message is displayed (password is required)
           * * Expected result:*
           * Warning messages "Email|Password is required." are displayed
    */
    @Test (testName = "tc_l2")
    public void loginEmptyFieldsWarningMessage(){
        logger.info("Starting tc_l2 on empty fields");
        LoginPage myLoginPage = new LoginPage(driver);
        myLoginPage.click(myLoginPage.userName);
        myLoginPage.click(myLoginPage.password);
        Assert.assertEquals(myLoginPage.emailErrorMsg.getText(), "Email is required.");
        myLoginPage.click(myLoginPage.userName);
        Assert.assertEquals(myLoginPage.passwordErrorMsg.getText(), "Password is required.");

        /*
        @Test
        public void loginEmptyFieldsWarningMessageTest(){
        Login login = new Login(dr);
         login.clicksOnFields();
        Assert.assertEquals(dr.findElement(By.id("email-error-required")).getText(), "Email is required.");
        Assert.assertEquals(dr.findElement(By.id("password-error-required")).getText(), "Password is required.");
    }
        public void clicksOnFields() {
        userName.click();
        emptySpace.click();
        password.click();
        emptySpace.click();
    }
        @FindBy(xpath = "//*[@id=\"login-form\"]/div[1]/div[2]")
        WebElement emptySpace;
         */

    }

    /*  TODO:
         ID*: tc_l3
         Pre-conditions:* Logged out, login page opened
         Title:* Check login button is enabled when required fields are filled with
         correct email and password => 8 chars
         *Steps:*
         1.Check login button is disabled
         2. enter "test@gmail.com" into email field
         3. enter "test@gmail.com" into password field
         4. Check if Login button is active
         *Expected result:
          * 1.Login button is disabled.
          * 4.Login button is enabled.
     */
    @Test (testName = "tc_l3")
    public void LoginButtonEnabledWithCorrectEmailAndPass() {
        logger.info("Starting tc_l3 Check login button is enabled when required fields are filled with correct email and password");
        logger.info("1. enter \"test@gmail.com\" into email field");
        logger.info("2. enter \"test@gmail.com\" into password field");
        LoginPage myLoginPage = new LoginPage(driver);
        Assert.assertFalse(myLoginPage.loginButton.isEnabled());
        logger.info("Check if Login button is active");
        myLoginPage.enterCreds("test@gmail.com", "test@gmail.com");
        Assert.assertTrue(myLoginPage.loginButton.isEnabled());
    }

    @Test
    public void emailMatcher() {
        ContactsPage contactsPage = new ContactsPage(driver);
        positiveLogin();

    }
    @Test
    public void loginNegativeTestWithFaker(){
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        LoginPage myLoginPage = new LoginPage(driver);
        myLoginPage.loginNegative(email,password);

        sleep();
        Assert.assertEquals(myLoginPage.registerLink.getText(), "Password is required.");

    }
}
