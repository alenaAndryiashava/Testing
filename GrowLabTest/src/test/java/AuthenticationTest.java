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
        logger.info("Precondition for each test: user is not logged in, login page is opened in browser");
        openLoginPage();
        //title[@lang=”en”]
        //element[@attribute=“value”]
        //h3[@data-id=“.....”]
    }
    @Test
    public void LoginAsManagerTest(){
        logger.info("Starting method: LoginAsManagerTest");
        logger.info("Authenticate as manager");
        managerAuth();
        sleepMethod();
        List<WebElement> navItems = driver.findElements(By.className("nav-item"));
        List<WebElement> dropDowns = driver.findElements(By.className("dropdown"));
        navItems.removeAll(dropDowns);
        Assert.assertEquals(navItems.size(), 4);
        logger.info("Verify that links to portal sections are presented on the page available to manager");

        driver.findElement(By.partialLinkText("PROJECT OVERVIEW"));
        driver.findElement(By.partialLinkText("CLIENTS"));
        driver.findElement(By.partialLinkText("TEAM"));
        driver.findElement(By.partialLinkText("INVOICES"));
        sleepMethod();
        Assert.assertTrue(searchInPageSource("PROJECT OVERVIEW"));
        Assert.assertTrue(searchInPageSource("CLIENTS"));
        Assert.assertTrue(searchInPageSource("TEAM"));
        Assert.assertTrue(searchInPageSource("INVOICES"));
        logger.info("Test passed");
    }
    @Test(dataProvider = "getPartialLinkText")
    public void LoginAsManagerDataProviderTest(String text){
        logger.info("Starting method: LoginAsManagerTest");
        logger.info("Authenticate as manager");
        managerAuth();
        sleepMethod();
        driver.findElement(By.partialLinkText(text));
        sleepMethod();
        Assert.assertTrue(searchInPageSource(text));
        logger.info("Test passed");
    }
    @Test(dataProvider = "getManagerDataAuthFromCSV")
    public void LoginAsManagerFromCSVTest(String email,String password,String project, String clients, String team, String invoices){
        logger.info("Starting method: LoginAsManagerTest");
        logger.info("Authenticate as manager");
        auth(email,password);
        sleepMethod();
        driver.findElement(By.partialLinkText(project));
        driver.findElement(By.partialLinkText(clients));
        driver.findElement(By.partialLinkText(team));
        driver.findElement(By.partialLinkText(invoices));
        sleepMethod();
        Assert.assertTrue(searchInPageSource(project));
        Assert.assertTrue(searchInPageSource(clients));
        Assert.assertTrue(searchInPageSource(team));
        Assert.assertTrue(searchInPageSource(invoices));
        logger.info("Test passed");

    }


    @Test
    public void LoginAsClientTest(){
        logger.info("Running test LoginAsClient");
        logger.info("Authenticate as client");
        clientAuth();
        sleepMethod();
        logger.info("Verify that links to portal sections are presented on the page available to client");
        driver.findElement(By.partialLinkText("PROJECTS OVERVIEW"));
        driver.findElement(By.partialLinkText("INVOICES"));
        //Verify there is no more links from manager/consultant: **first way**
        Assert.assertFalse(searchInPageSource("CLIENTS"));
        Assert.assertFalse(searchInPageSource("TEAM"));
        //Verify there is no more links from manager/consultant: **second way**
        //Attention to that findElements is used instead findElement to get an empty array (expected)
        Assert.assertTrue(driver.findElements(By.partialLinkText("CLIENTS")).isEmpty());
        Assert.assertTrue(driver.findElements(By.partialLinkText("TEAM")).isEmpty());

    }
    @Test
    public void LoginAsConsultant() {
        logger.info("Running test LoginAsConsultant");
        logger.info("Authenticate as a consultant");
        consultantAuth();
        sleepMethod();

        /*List<WebElement> navItems = driver.findElements(By.className("nav-item"));
        List<WebElement> dropDowns = driver.findElements(By.className("dropdown"));
        navItems.removeAll(dropDowns);
        Assert.assertEquals(navItems.size(), 4);

         */
        logger.info("Verify that links to portal sections are presented on the page available to consultant");
        driver.findElement(By.partialLinkText("PROJECT OVERVIEW"));
        driver.findElement(By.partialLinkText("CLIENTS"));
        driver.findElement(By.partialLinkText("TEAM"));
        driver.findElement(By.partialLinkText("INVOICES"));
        sleepMethod();
        Assert.assertTrue(searchInPageSource("PROJECT OVERVIEW"));
        Assert.assertTrue(searchInPageSource("CLIENTS"));
        Assert.assertTrue(searchInPageSource("TEAM"));
        Assert.assertTrue(searchInPageSource("INVOICES"));
    }

    @AfterMethod
    public void afterMLogout(){
        logger.info("Ending test");
        logout();
    }
}
