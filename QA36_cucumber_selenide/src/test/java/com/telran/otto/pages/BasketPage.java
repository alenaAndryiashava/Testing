package com.telran.otto.pages;

import com.codeborne.selenide.Conditional;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java8.En;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$;

public class BasketPage implements En {

    private static By formBasket = By
            .cssSelector("#basket");
    private static By emptyBasketMessage = By
            .cssSelector("[data-qa='emptyBasketMessage']");

    private static By articleInfo = By
            .cssSelector("[data-qa='info1']");


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
}
