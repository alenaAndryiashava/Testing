package tests;

import dto.Card;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CompletePage;
import pages.HomePage;
import pages.LoginPage;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.sleep;


public class CompleteOrderTest extends BaseTest {
    @BeforeMethod
    public void login() {
        new LoginPage()
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .login();
    }

    @Test
    public void completeOrder() {
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
                .checkout()
                .setName("Andrey")
                .setLastName("Damasevich")
                .setPostalCode("12345")
                .continueOrder();

        CompletePage completePage = new CompletePage();

        ArrayList<String> names = completePage.getAllCards();
        double sum = 0;

        for (int i = 0; i < names.size(); i++) {
            Card cpcard = completePage.saveCardData(names.get(i));
            Double price = Double.parseDouble(cpcard.getPrice().replace("$", ""));
            sum += price;
        }

        sum += completePage.getTax();

        System.out.println(sum);

        completePage
                .checkTotalSum(sum);

        sleep(5000);

    }
}







