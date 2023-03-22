package com.telran.otto.pages;



import com.codeborne.selenide.SelenideElement;
import com.telran.otto.CommonData;
import io.cucumber.java8.En;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class BasketPage implements En {

    private static By formBasket = By
            .cssSelector("#basket");
    private static By emptyBasketMessage = By
            .cssSelector("[data-qa='emptyBasketMessage']");

    private static By articleInfo = By
            .cssSelector("[data-qa='info1']");

    SelenideElement productCartTitle = $x("//div[@data-qa=\"articleName\"]//a");


    private static By deleteIcon = By
            .cssSelector("form[class='or_basketItem__deleteForm'] button[title='Position löschen']");

    public SelenideElement basketFormIsShown() {
        return $(formBasket);
    }

    public SelenideElement isEmpty() {
        return $(emptyBasketMessage);

    }

    public SelenideElement selectedItemInBasket() {
        return $(articleInfo);
    }

    public void deleteItem() {
        $(deleteIcon).click();

    }

    public void shouldBeEqualTitle() {
        productCartTitle.shouldHave(text(CommonData.cardTitle));
    }
}
