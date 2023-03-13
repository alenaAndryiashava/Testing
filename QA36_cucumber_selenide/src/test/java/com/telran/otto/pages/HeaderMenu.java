package com.telran.otto.pages;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java8.En;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;


public class HeaderMenu implements En {


    private static By miniBasket_amount = By
            .cssSelector("[data-qa='miniBasketAmount']");


    private static By damenMode = By
            .cssSelector("#nav_menu > div > div > div > ul > li:nth-child(3) > a");



    public SelenideElement verifyMiniBasket() {
        return $(miniBasket_amount);
    }

    public void goToDamen_Mode() {
        $(damenMode).click();

    }

    public void goToBasket() {
        $(miniBasket_amount).click();
    }

}
