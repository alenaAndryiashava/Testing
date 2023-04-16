package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import dto.Card;
import pages.HomePage;
import pages.LoginPage;

public class HomeTest extends BaseTest{
@BeforeMethod
public void login(){
    new LoginPage()
            .setLogin("standard_user")
            .setPassword("secret_sauce")
            .login();
}
    @Test
    public void checkCloseError() {
         new HomePage()
                 .checkLogin();

    }

    @Test
    public void checkFilter() {
        new HomePage()
                .checkFilter();

    }

    @Test
    public void checkBasket() {
        new HomePage()
                .checkBasket();

    }
    @Test
    public void checkBasketCounter() {
        new HomePage()
                .checkBasketCounter();
    }
    @Test
    public void checkCardsSize() {
        new HomePage()
                .checkProducts(6);

    }
    @Test(testName  = "Наличие названия товара, описания, изображения, цены, кнопки")
    public void checkCardName() {
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
                .checkCardData(card)
                .checkBasketCounter(3);

        //System.out.println("После добавления " + homePage.getBasketCounter());

    }
    @Test(testName  = "Проверка данных товара на странице товара")
    public void checkProductDetails() {
    HomePage homePage = new HomePage();
    Card card = homePage.saveCardData("Sauce Labs Fleece Jacket");

    card
            .openCardPage()
            .checkCardData(card);
    }
}
