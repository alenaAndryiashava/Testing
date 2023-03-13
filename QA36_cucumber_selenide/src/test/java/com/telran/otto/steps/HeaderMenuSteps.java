package com.telran.otto.steps;

import com.codeborne.selenide.Condition;
import com.telran.otto.pages.HeaderMenu;
import io.cucumber.java8.En;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.page;

public class HeaderMenuSteps implements En {
    HeaderMenu headerMenu;

    public HeaderMenuSteps () {

        When("we press  category Damen-Mode on a header menu", () -> {
            headerMenu = page(HeaderMenu.class);
            headerMenu.goToDamen_Mode();
        });
        Then("we see that article  is in basket", () -> {
            headerMenu.verifyMiniBasket().shouldBe(Condition.visible);
        });
        When("we press Warenkorb icon", () -> {
            headerMenu.goToBasket();
        });
    }
}
