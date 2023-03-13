package com.telran.otto.pages;

import io.cucumber.java8.En;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Gallery implements En {
    private static By selectedArticle = By
            .cssSelector("[data-qa='ftfind-product-1']");

    public void goToProductPage() {
        $(selectedArticle).click();
    }

}
//$$(selector).
//$$("#search-results a").findBy(text("selenide.org")).click();
