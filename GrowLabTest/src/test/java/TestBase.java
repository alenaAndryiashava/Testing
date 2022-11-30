import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {
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

    public void auth(String emailEntered, String passwordEntered){
        sleepMethod();
        enterEmail(emailEntered);
        enterPassword(passwordEntered);
        submitButtonClick();
        sleepMethod();
        checkClientsLink();
        sleepMethod();
    }
    public void managerAuth() {
        auth("billye@example.com","123456");
    }
    public void clientAuth(){
        auth("lucie@example.com", "123456");
    }
    public void consultantAuth(){
        auth("edra@example.com", "123456");
    }

    public void checkClientsLink(){
        sleepMethod();
        WebElement clients = driver.findElement(By.cssSelector("#cta7 > div > div > div.col-md-7.col-12.text-center.text-md-left > h2"));
        clients.click();
        sleepMethod();
        //Assert.assertTrue(searchInPageSource("Welcome to your Client Portal"));
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

    @AfterTest
    public void exit() {
        logger.info("Leaving the site. Browser is closing.");
        driver.quit();
    }
}

