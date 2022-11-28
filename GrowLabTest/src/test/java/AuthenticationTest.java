import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;

public class AuthenticationTest extends TestBase{

    @BeforeMethod
    public void beforeMOpenLoginPage(){
        //Precondition for each test: user is not logged in, login page is opened in browser
        openLoginPage();
    }
    @Test
    public void LoginAsManagerTest(){
        //auth as manager
        managerAuth();
        sleepMethod();
        List<WebElement> navItems = driver.findElements(By.className("nav-item"));
        List<WebElement> dropDowns = driver.findElements(By.className("dropdown"));
        navItems.removeAll(dropDowns);
        Assert.assertEquals(navItems.size(), 4);
    }

    @Test
    public void LoginAsClientTest(){
        //auth as client
        clientAuth();
        sleepMethod();
        driver.findElement(By.partialLinkText("PROJECTS OVERVIEW"));
        driver.findElement(By.partialLinkText("INVOICES"));
        //Verify there is no more links from manager/consultant: **second way**
        //Attention to that findElements is used instead findElement to get an empty array (expected)
        Assert.assertTrue(driver.findElements(By.partialLinkText("CLIENTS")).isEmpty());
        Assert.assertTrue(driver.findElements(By.partialLinkText("TEAM")).isEmpty());

    }
    @Test
    public void LoginAsConsultant()  {
        //Authenticate as a consultant
        consultantAuth();
        sleepMethod();

        List<WebElement> navItems = driver.findElements(By.className("nav-item"));
        List<WebElement> dropDowns = driver.findElements(By.className("dropdown"));
        navItems.removeAll(dropDowns);
        Assert.assertEquals(navItems.size(), 4);
    }

    @AfterMethod
    public void afterMLogout(){
        logout();

    }


}
