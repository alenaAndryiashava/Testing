import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AuthenticationTest extends TestBase{

    @BeforeMethod(alwaysRun=true)
    public void beforeMOpenLoginPage(Method m, Object[] p){
        logger.info("Starting method: " + m.getName()+" with data: "+ Arrays.asList(p));

        //Precondition for each test: user is not logged in, login page is opened in browser
        openLoginPage();
    }
        //title[@lang=”en”]
        //element[@attribute=“value”]
        //h3[@data-id=“.....”]

    @Test(priority = 3)
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

    @Test(priority = 3, enabled=false)
    public void thisTestShouldFail_LoginAsManager(){
        logger.info("Running test LoginAsManager");
        logger.info("Starting method login");

        //Authenticate as a manager
        managerAuth();
        driver.findElement(By.partialLinkText("ljksand93923s"));
    }


    @Test(priority = 3)
    public void LoginAsClientTest(){
        logger.info("Running test LoginAsClient");
        logger.info("Authenticate as client");
        clientAuth();
        sleepMethod();
        logger.info("Verify that links to portal sections are presented on the page available to client");
        //Verify that links to portal sections are presented on the page available to client
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
    @Test(priority = 3)
    public void LoginAsConsultant() {
        logger.info("Running test LoginAsConsultant");
        logger.info("Authenticate as a consultant");
        consultantAuth();
        sleepMethod();
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

    @Test(dataProvider = "positiveDataAuthFromCSV", dataProviderClass=DataProviders.class, priority = 1, groups = "smoke")
    public void goodAuthTestWithDataProviderCSV(String email, String pwd, String elementsTrue, String elementsFalse) throws InterruptedException {
        auth(email, pwd);

        //Check if splitted by ; strings from elementsTrue are presented on the page as links
        String[] presented = elementsTrue.split(";");
        for (String verification : presented) {
            driver.findElement(By.partialLinkText(verification));
        }

        //Check if split by ; strings from elementsFalse are not presented on the page as links (excluding space characters from verifications)
        String[] notpresented = elementsFalse.split(";");
        for (String verification : notpresented) {
            if (!Objects.equals(verification, ""))
            {
                Assert.assertEquals(driver.findElements(By.partialLinkText(verification)).size(), 0);
            }
        }
    }
    @Test(dataProvider = "wrongDataAuthFromCSV", dataProviderClass = DataProviders.class, priority = 0, groups = "smoke")
    public void BadAuthTestWithDataProvider(String email, String pwd) throws InterruptedException {
        auth(email, pwd);
        String text = "Invalid email or password";
        Assert.assertEquals(driver.getPageSource().contains(text),Boolean.TRUE);
    }

    @AfterMethod
    public void afterMLogout(){
        logger.info("Ending test");
        logout();
    }
}
