package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class TestBase {
    protected WebDriver driver;
    protected Faker faker = new Faker();
    String urlBank = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
    String urlGlobalSqa = "https://www.globalsqa.com/samplepagetest/";

    @BeforeMethod
    public void setUp() {
        // driver = new ChromeDriver();
        //driver = new FirefoxDriver();
        //driver = new SafariDriver();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
//        driver.get(urlGlobalSqa);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
