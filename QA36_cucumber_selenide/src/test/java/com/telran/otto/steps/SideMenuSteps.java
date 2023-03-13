package com.telran.otto.steps;

import com.telran.otto.pages.SideMenu;
import io.cucumber.java8.En;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.page;

public class SideMenuSteps implements En {
    WebDriver driver;
    SideMenu sideMenu;

    public SideMenuSteps () {

        When("we press  bekleidung on a side menu", () -> {
            sideMenu = page(SideMenu.class);
            sideMenu.goToBekleidung();
        });
        When("we press  blusen on a side menu", () -> {
            sideMenu.goToBlusen();
        });
    }
}
