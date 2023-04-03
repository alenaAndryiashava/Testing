package tests;

import org.testng.annotations.Test;
import pages.Card;
import pages.LoginPage;

public class HomeTest extends BaseTest{

    @Test
    public void checkCloseError() {
         new LoginPage()
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .login()
                 .checkLogin();

    }

    @Test
    public void checkFilter() {
        new LoginPage()
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .login()
                .checkFilter();

    }

    @Test
    public void checkBasket() {
        new LoginPage()
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .login()
                .checkBasket();

    }
    @Test
    public void checkBasketCounter() {
        new LoginPage()
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .login()
                .checkBasketCounter();
    }
    @Test
    public void checkCardsSize() {
        new LoginPage()
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .login()
                .checkProducts(6);

    }
    @Test
    public void checkCardName() {
        Card card = new Card("Sauce Labs Backpack");
        new LoginPage()
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .login()
                .checkCardName(card);

        System.out.println(card);

    }
}
