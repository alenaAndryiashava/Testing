import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SeleniumTest {
    WebDriver driver;
    //before
    @BeforeMethod
    public void prepare(){
        driver = new ChromeDriver();
        driver.get("http://google.com");

    }

    //test
    @Test
    public void test() throws InterruptedException{
        Thread.sleep(5000);

    }

    //after
    @AfterTest
    public void afterTest(){
     driver.quit();
    }
}

