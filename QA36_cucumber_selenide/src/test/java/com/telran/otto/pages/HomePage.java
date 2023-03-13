package com.telran.otto.pages;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    private static By okCookiesButton = By
            .cssSelector("#cookieBanner > div > div > div.cookieBanner__footer > button");

    private static By meinKontoIcon = By.cssSelector("[data-qa='user_login_area_icon']");

    private static By anmeldenButton = By.cssSelector("[data-qa='user_login_area_login']");
    private static By loggedInIcon = By.cssSelector("[data-qa='user_login_area_badge_logged_in']");
    public void acceptCookies() {
        $(okCookiesButton).click();

    }
    public void goToMeinKonto() {
        sleep(1000);
        System.out.println("enabled "+$(meinKontoIcon).isEnabled());
        $(meinKontoIcon).click(ClickOptions.usingJavaScript());
        sleep(1000);
        System.out.println("exists "+$(anmeldenButton).exists());
        $(anmeldenButton).click();
    }
    public SelenideElement verifyAuthIcon() {
        return $(loggedInIcon);
    }


}
