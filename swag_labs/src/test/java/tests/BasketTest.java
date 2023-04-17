package tests;

import dto.Card;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class BasketTest extends BaseTest {
    @BeforeMethod
    public void login() {
        new LoginPage()
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .login();

    }
    @Test
    public void addToBasketAndDeleteProductFromBasket() {
        HomePage homePage = new HomePage();
        Card card = homePage.saveCardData("Sauce Labs Backpack");
        Card card1 = homePage.saveCardData("Sauce Labs Onesie");
        Card card2 = homePage.saveCardData("Test.allTheThings() T-Shirt (Red)");

        System.out.println("До добавления " + homePage.getBasketCounter());

        card
                .addToBasket();

        card2
                .addToBasket();

        card1
                .addToBasket();

        homePage
                .openBasket()
                .deleteProduct()
                .deleteProduct()
                .deleteProduct();
    }
}
