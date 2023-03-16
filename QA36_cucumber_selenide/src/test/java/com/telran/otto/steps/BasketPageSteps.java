package com.telran.otto.steps;

import com.telran.otto.pages.BasketPage;
import io.cucumber.java8.En;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class BasketPageSteps implements En {
    BasketPage basket;

    public BasketPageSteps () {

        Then("we see selected article in a Basket-form", () -> {
            basket = page(BasketPage.class);
            basket.basketFormIsShown().shouldHave(text("Mein Warenkorb"));

            basket.selectedItemInBasket().shouldBe(visible);


        });
        When("we press delete Button in the basketItem", () -> {
            basket.deleteItem();
        });
        Then("selected product is deleted", () -> {
            basket.isEmpty()
                    .shouldHave(text("Es befinden sich keine Artikel in deinem Warenkorb."));

        });
    }
}
