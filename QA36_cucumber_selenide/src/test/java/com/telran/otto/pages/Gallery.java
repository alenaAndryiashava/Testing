package com.telran.otto.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.telran.otto.CommonData;
import io.cucumber.java8.En;
import org.openqa.selenium.By;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class Gallery implements En {
    private static By selectedArticle = By
            .cssSelector("[data-qa='ftfind-product-1']");

    SelenideElement title = $x("//article[@data-qa='ftfind-product-1']//h2");
    SelenideElement price = $x("//article[1]//ul[1]//li[1]//div[1]//div[2]//div[1]//p[1]//span[1]//span[1]");

    public void goToProductPage() {
        $(selectedArticle).click();
    }

    public void getTitle() {
        title.shouldNotBe(Condition.empty);
        CommonData.cardTitle = title.text();
    }

    public void getPrice() {
        price.shouldNotBe(Condition.empty);
        String str = price.text();
        String [] priceValue = str.split(" ");
        List<String> priceList = Arrays.asList(priceValue);
        CommonData.cardPrice = priceList.get(1);
        System.out.println("1213323");
    }

}
//$$(selector).
//$$("#search-results a").findBy(text("selenide.org")).click();
