import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    WebDriver driver ;

    @BeforeMethod
    public void prepare() {
        driver = new ChromeDriver();
        driver.get("https://derrick686.softr.app/login");
        //driver.manage().window().maximize();
    }

    public void enterBadEmail(){
        sleepMethod();
        WebElement input = driver.findElement(By.cssSelector("#sw-form-capture-email-input"));
        input.click();
        input.sendKeys("edraexample.com");
        sleepMethod();
        WebElement button = driver.findElement(By.cssSelector("#sw-sign-in-submit-btn"));
        button.click();
        sleepMethod();
        input.clear();
    }


    public void enterManagerEmail(){
        sleepMethod();
        WebElement email = driver.findElement(By.cssSelector("#sw-form-capture-email-input"));
        email.click();
        email.sendKeys("billye@example.com");
        sleepMethod();

    }
    public void enterCorrectPassword(){
        sleepMethod();
        WebElement password = driver.findElement(By.cssSelector("#sw-form-password-input"));
        password.click();
        password.sendKeys("123456");
        sleepMethod();
    }
    public void enterWrongPassword(){
        //enter wrong password
        sleepMethod();
        WebElement password = driver.findElement(By.cssSelector("#sw-form-password-input"));
        password.click();
        password.sendKeys("abcd");
        WebElement button = driver.findElement(By.cssSelector("#sw-sign-in-submit-btn"));
        button.click();
        sleepMethod();
        password.clear();
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
    public void checkStartPage(){
        //checking the transition to the start page
        sleepMethod();
        String text = driver.getPageSource();
        System.out.println(text.contains("Clients"));
        Assert.assertEquals(text.contains("Clients"),Boolean.TRUE);

    }
    public void managerAuth(){
        sleepMethod();
        enterManagerEmail();
        enterCorrectPassword();
        submitButtonClick();
        sleepMethod();
        checkStartPage();
        sleepMethod();
    }
    public void checkClientsLink(){
        //
        sleepMethod();
        WebElement clients = driver.findElement(By.cssSelector("#home-header1 > div > div.desktop-menu.text-center.justify-content-end > ul > li:nth-child(2) > a"));
        clients.click();
        sleepMethod();
        String text = driver.getPageSource();
        System.out.println(text.contains("Our Clients"));
        Assert.assertEquals(text.contains("Our Clients"),Boolean.TRUE);
    }

    public void sleepMethod(){
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            System.out.println("InterruptedException");
        }
    }

    @AfterMethod
    public void afterTest() {
        driver.close();
    }
}

