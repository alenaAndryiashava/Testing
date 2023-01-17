package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.ContactsPage;
import pages.LoginPage;

import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    WebDriver driver;
    ContactsPage contactsPage;


    public static Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeMethod
    public void init(){
        System.setProperty("webdriver.chrome.driver", "/Users/alenaandryiashava/Testing/Tools/chromedriver108");
        driver = new ChromeDriver();
        openLoginPage();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterMethod(enabled = true)
    public void tearDown(){
        driver.quit();
    }

    public static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void openLoginPage(){
        driver.get("http://phonebook.telran-edu.de:8080/user/login");
    }

    public void positiveLogin(){
        LoginPage myLoginPage = new LoginPage(driver);
        ContactsPage contactsPage = myLoginPage.login("test@gmail.com", "test@gmail.com");
        sleep();
    }
    public Boolean searchInPageSource(String text){
        return driver.getPageSource().contains(text);

    }
    public void selectLanguage(String language){
        //String language = System.getProperty("language");
        contactsPage = new ContactsPage(driver);
        contactsPage.selectLanguage(language);
    }

}
