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
        String text = driver.getPageSource();
        System.out.println(text.contains("Welcome to your Client Portal"));
        Assert.assertEquals(text.contains("Welcome to your Client Portal"),Boolean.TRUE);
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


    @AfterMethod
    public void exit() {
        driver.close();
    }
}

