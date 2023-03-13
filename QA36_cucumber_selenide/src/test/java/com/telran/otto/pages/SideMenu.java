package com.telran.otto.pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SideMenu {
    private static By blusen = By
            .xpath("//li[@data-title='Blusen']");
    private static By bekleidung = By
            .xpath("//li[@class='nav_local-link nav_link-headline']//span[@class='nav_link-title'][normalize-space()='Bekleidung']");


    public void goToBekleidung() {
        $(bekleidung).click();
    }
    public void goToBlusen() {
        $(blusen).click();
    }


}


