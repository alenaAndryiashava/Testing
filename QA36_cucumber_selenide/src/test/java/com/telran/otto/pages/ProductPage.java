package com.telran.otto.pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.telran.otto.CommonData;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ProductPage {

    private static By addToBasketButton = By
            .cssSelector("[data-qa='addToBasket']");

    private static By articleInfo = By
            .cssSelector("[data-qa='name']");

    private static By itemInfo = By
            .cssSelector("[data-qa='itemInfo']");
    private static By selectedItem = By
            .cssSelector("[data-qa='variationName']");


    private static By dialogClose = By
            .xpath("//a[contains(text(),'X')]");

    public void addToBasket() {
        $(addToBasketButton).click();

    }

    public  SelenideElement selectedProductIsAdded() {
        return $(itemInfo);
    }

    SelenideElement productPagePrice = $x("//span[@class=\"pl_headline300\"]");

    public void dialogWindowClose() {
        $(dialogClose).click();

    }

    public SelenideElement formIsShown() {
        return $(selectedItem);
    }

    public void shouldBeEqualPrice() {
        productPagePrice.shouldHave(Condition.text(CommonData.cardPrice));
        System.out.println("1121241");
    }

}
