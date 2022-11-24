import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class TestSelenium {
    WebDriver driver;
    @BeforeTest
    public void prepare(){
        driver = new ChromeDriver();
        driver.get("https://google.com");
        driver.manage().window().maximize();
        driver.findElement(By.id("L2AGLb")).click();
    }
    @Test
    public void searchTest() throws InterruptedException {

        Thread.sleep(2000);
        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        search.click();
        search.sendKeys("Selenium");
        Thread.sleep(2000);
        search.sendKeys(Keys.RETURN);
        Thread.sleep(2000);

        String source = driver.getPageSource();
        String seleniumText = "selenium.dev";
        System.out.println(source.contains(seleniumText));
        Thread.sleep(2000);
    }

    @AfterTest
    public void afterTest(){
        driver.close();
    }

}
