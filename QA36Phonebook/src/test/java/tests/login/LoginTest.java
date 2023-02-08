package tests.login;

import com.github.javafaker.Faker;
import org.example.pages.LoginPage;
import org.example.pages.MenuPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.BaseTest;

public class LoginTest extends BaseTest {
    Faker faker = new Faker();
    LoginPage loginPage;
    MenuPage menu;

    String email = "test@gmail.com";
    String password = "test@gmail.com";

    String wrongEmail = faker.internet().emailAddress();
    String wrongPassword = faker.internet().password();

    String errorMessage = "Please check your activation or Login + Password combination";

    @Test
    public void loginTest(){
        loginPage = new LoginPage(driver);
        loginPage.getAuth(email,password);

        menu = new MenuPage(driver);
        Assert.assertTrue(menu.waitElementVisible(menu.getAccountButton()));
    }

    @Test
    public void loginTestWithWrongEmailAndPassword(){
        loginPage = new LoginPage(driver);
        loginPage.getAuth(wrongEmail, wrongPassword);

        Assert.assertTrue(loginPage.waitElementVisible(loginPage.getErrorMsg()));
        Assert.assertEquals(loginPage.getErrorMsgText(),errorMessage,
                "The actual text of error message does not matches the the expected text");
    }
}
