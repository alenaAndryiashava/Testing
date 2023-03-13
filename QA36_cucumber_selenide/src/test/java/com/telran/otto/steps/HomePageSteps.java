package com.telran.otto.steps;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import com.telran.otto.pages.HeaderMenu;
import com.telran.otto.pages.HomePage;
import com.telran.otto.pages.LoginPage;
import io.cucumber.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;

public class HomePageSteps implements En {
    WebDriver driver;
    HomePage homePage;

    String baseUrl = "https://www.otto.de/";

    public HomePageSteps(){
        Given("that we navigate to home page", () -> {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            WebDriverRunner.setWebDriver(driver);
            homePage = open(baseUrl, HomePage.class);
        });


        /*Given("that we navigate to home page", () -> {
            driver = new FirefoxDriver();
            WebDriverRunner.setWebDriver(driver);
            homePage = open(baseUrl, HomePage.class);
        });

         */

        When("we accept cookies", () -> {
            homePage.acceptCookies();
        });

        When("we press Mein konto icon", () -> {
            homePage.goToMeinKonto();
        });

        Then("Home page is loaded", () -> {
            homePage.verifyAuthIcon().shouldBe(Condition.visible);
        });
        And("we close the driver", () -> {
            driver.close();
        });

    }
}
