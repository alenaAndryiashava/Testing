package com.telran.otto.steps;

import com.telran.otto.pages.Gallery;
import com.telran.otto.pages.ProductPage;
import io.cucumber.java8.En;
import com.codeborne.selenide.Condition;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.page;

public class ProductPageSteps implements En {

    ProductPage product;
    public ProductPageSteps() {
        Then("we see selected product in Product page", () -> {
            product = page(ProductPage.class);
            product.formIsShown().shouldBe(Condition.visible);
        });
        When("we press addToBasket button", () -> {
            product = page(ProductPage.class);
            product.addToBasket();
        });
        Then("we see dialog window,that article moved to the basket", () -> {
            product.selectedProductIsAdded().shouldHave(text("Zum Warenkorb hinzugefügt!"));

        });

        When("we close dialog window", () -> {
            product.dialogWindowClose();
        });
    }
}
