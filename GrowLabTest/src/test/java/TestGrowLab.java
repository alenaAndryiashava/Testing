
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;


public class TestGrowLab extends TestBase {

    @Test
    public void wrongEmail_emptyPasswordTest(){
        //try to auth with bad email and empty password
        enterEmail("edraexample.com");
        sleepMethod();
        checkErrorMessage();
    }

    @Test
    public void correctEmail_wrongPasswordTest(){
        //try to auth with correct email and wrong password
        enterEmail("edra@example.com");
        enterPassword("123");
        sleepMethod();
        checkErrorMessage();
    }

    @Test
    public void manager_AuthTest(){
        //auth as manager
        managerAuth();
        sleepMethod();
        checkClientsLink();
        sleepMethod();
        logout();
    }


    @Test
    public void goodAuth_afterBadAuthTest(){
        //auth as manager after uncorrected auth
        enterEmail("edraexample.com");
        submitButtonClick();
        sleepMethod();
        checkErrorMessage();
        enterEmail("edra@example.com");
        enterPassword("123456");
        submitButtonClick();
        sleepMethod();
        checkClientsLink();
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
        WebElement clients = driver.findElement(By.xpath("//*[@id=\"home-header1\"]/div/div[1]/ul/li[2]/a"));
        clients.click();
        sleepMethod();
        WebElement input = driver.findElement(By.cssSelector("#list2 > div:nth-child(1) > div > div > div > input"));
        input.sendKeys("april");
        sleepMethod();
        Assert.assertTrue(searchInPageSource("No results found, try adjusting your search and filters."));
        //String text = driver.getPageSource();
        //System.out.println(text.contains("No results found, try adjusting your search and filters."));
        //Assert.assertEquals(text.contains("No results found, try adjusting your search and filters."),Boolean.TRUE);
    }
    //3. Проверка поиска клиентов по названию компании, в которой работает один клиент
    @Test
    public void clientsSearch_byCompany_oneClient(){
        //Checking the search for clients by the name of the company in which one client works
        managerAuth();
        checkClientsLink();
        sleepMethod();
        WebElement clients = driver.findElement(By.xpath("//*[@id=\"home-header1\"]/div/div[1]/ul/li[2]/a"));
        clients.click();
        sleepMethod();
        WebElement input = driver.findElement(By.cssSelector("#list2 > div:nth-child(1) > div > div > div > input"));
        input.sendKeys("Worman");
        sleepMethod();

        int clientsFound = driver.findElements(By.cssSelector("#list2 > div:nth-child(2) > div > div > div.sw-js-list-container.sw-3-column > div:nth-child(1) > div > div.text.d-flex.flex-column > div > div:nth-child(2)")).size();
        Assert.assertEquals(clientsFound, 1);
    }
    @Test
    public void clientsSearch_byCompany_twoClient(){
        managerAuth();
        checkClientsLink();
        sleepMethod();
        WebElement clients = driver.findElement(By.xpath("//*[@id=\"home-header1\"]/div/div[1]/ul/li[2]/a"));
        clients.click();
        sleepMethod();
        WebElement input = driver.findElement(By.cssSelector("#list2 > div:nth-child(1) > div > div > div > input"));
        input.sendKeys("montag");
        sleepMethod();
        int clientsFound = driver.findElements(By.cssSelector(".js-list-item.position-relative")).size();
        Assert.assertEquals(clientsFound, 2);
    }


}
