import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestBase extends DataProviders{
    final static Logger logger = LoggerFactory.getLogger(TestBase.class);
    WebDriver driver ;

    @BeforeTest
    public void prepare() {
        driver = new ChromeDriver();
        driver.get("https://derrick686.softr.app/login");
        driver.manage().window().maximize();
        logger.info("Running a test: prepare");
        logger.info("Precondition for each test: opening login page in browser");
    }
    @DataProvider
    public Iterator<Object[]> getWrongLoginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"email","password"});
        list.add(new Object[]{"billyeexample.com","123456"});
        list.add(new Object[]{"","123456"});
        list.add(new Object[]{"billye@example.com",""});
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> getPartialLinkText(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"PROJECT OVERVIEW"});
        list.add(new Object[]{"CLIENTS"});
        list.add(new Object[]{"TEAM"});
        list.add(new Object[]{"INVOICES"});
        return list.iterator();
    }

    public void enterEmail(String emailEntered){
        sleepMethod();
        WebElement email = driver.findElement(By.cssSelector("#sw-form-capture-email-input"));
        email.click();
        email.clear();
        email.sendKeys(emailEntered);
        sleepMethod();

    }
    public void enterPassword(String passwordEntered){
        sleepMethod();
        WebElement password = driver.findElement(By.cssSelector("#sw-form-password-input"));
        password.click();
        password.clear();
        password.sendKeys(passwordEntered);
        sleepMethod();
    }

    public void submitButtonClick(){
        //click submit button
        sleepMethod();
        WebElement button = driver.findElement(By.cssSelector("#sw-sign-in-submit-btn"));
        button.click();

    }
    public void checkErrorMessage(){
        //verification error message
        sleepMethod();
        String text = driver.getPageSource();
        System.out.println(text.contains("Invalid email or password"));
        Assert.assertEquals(text.contains("Invalid email or password"),Boolean.TRUE);

    }
    public void badAuth(String email, String password){
        sleepMethod();
        enterEmail(email);
        enterPassword(password);
        submitButtonClick();
        sleepMethod();
        checkErrorMessage();
        sleepMethod();
    }

    public void goodAuth(String email, String password){
        sleepMethod();
        enterEmail(email);
        enterPassword(password);
        submitButtonClick();
        sleepMethod();
        checkStartPage();
        logout();
        openLoginPage();
        sleepMethod();
    }
    public void managerAuth() {
        goodAuth("billye@example.com","123456");
    }
    public void clientAuth(){
        goodAuth("lucie@example.com", "123456");
    }
    public void consultantAuth(){
        goodAuth("edra@example.com", "123456");
    }

    public void checkStartPage(){
        sleepMethod();
        Assert.assertEquals(searchInPageSource("Welcome to your Client Portal"),Boolean.TRUE);
    }

    public void sleepMethod(){
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            System.out.println("InterruptedException");
        }
    }
    public void logout(){
        // log out of the manager account if we are currently
        driver.findElement(By.id("navbarDropdown")).click();
        driver.findElement(By.cssSelector(".d-item.d-flex.justify-content-start.align-items-center.navigate")).click();
    }
    public void openLoginPage(){
        driver.get("https://derrick686.softr.app/login");
    }
    public boolean searchInPageSource(String text){
        return driver.getPageSource().contains(text);

    }
    public void searchByLinkText(String text){
        driver.findElement(By.partialLinkText(text));
    }
    public void searchByCssSelector(String text,String word){
        sleepMethod();
        WebElement webElement =  driver.findElement(By.cssSelector(text));
        webElement.sendKeys(word);
        sleepMethod();
    }
    public void searchClientsBy(String stringS) {
        WebElement input = driver.findElement(By.xpath("//*[@id=\"list2\"]/div[1]/div/div/div/input"));
        input.click();
        input.clear();
        input.sendKeys(stringS);
        input.sendKeys(Keys.ENTER);
        sleepMethod();
    }


    @AfterTest
    public void exit() {
        logger.info("Leaving the site. Browser is closing.");
        driver.quit();
    }
}

