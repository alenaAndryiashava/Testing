
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class TestGrowLab extends TestBase {

    @Test
    public void wrongEmail_emptyPasswordTest(){
        //try to auth with bad email and empty password
        enterBadEmail();
        checkErrorMessage();
    }

    @Test
    public void correctEmail_wrongPasswordTest(){
        //try to auth with correct email and wrong password
        enterManagerEmail();
        enterWrongPassword();
        checkErrorMessage();
    }

    @Test
    public void manager_AuthTest(){
        //auth as manager
        enterManagerEmail();
        enterCorrectPassword();
        submitButtonClick();
        checkStartPage();
    }

    @Test
    public void goodAuth_afterBadAuthTest(){
        //auth as manager after uncorrected auth
        enterBadEmail();
        submitButtonClick();
        checkErrorMessage();
        enterManagerEmail();
        enterCorrectPassword();
        submitButtonClick();
        checkStartPage();
    }

    @Test
    public void clientsPageTest(){
        //manager auth and move to clients page
        managerAuth();
        checkClientsLink();
    }

    @Test
    public void clientsSearch_byNon_existentCompany(){
        //check clients search with non-existent Company
        managerAuth();
        checkClientsLink();
        sleepMethod();
        WebElement clients = driver.findElement(By.cssSelector("#list2 > div:nth-child(1) > div > div > div > input"));
        clients.click();
        clients.sendKeys("sdffgd");
        sleepMethod();
        String text = driver.getPageSource();
        System.out.println(text.contains("No results found, try adjusting your search and filters."));
        Assert.assertEquals(text.contains("No results found, try adjusting your search and filters."),Boolean.TRUE);
    }

}
