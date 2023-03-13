package com.example.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;


import java.time.Duration;
import java.util.List;
import java.util.Map;

public class LoginSteps {
    public WebDriver driver;

    @Given("navigate to Phonebook root path")
    public void navigateToLoginPage(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Then("PageBook Login page appears")
    public void pageBookLoginPageAppears() {
       Assert.assertTrue(
               isElementPresent(By.cssSelector("body > app-root > app-login > div > div.col-xl-6.text-right.text-white.bg-dark > div > div > h2"))
               );

    }

    @After
    public void tearDown(){
        driver.close();
    }
    public boolean isElementPresent(By locator){
        return driver.findElements(locator).size()>0;
    }

    public void type(By locator, String text){
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(text);
    }

    @Given("Enter valid username and password")
    public void enter_valid_username_and_password() {
        type(By.cssSelector("#defaultRegisterFormEmail"),"test@gmail.com");
        type(By.xpath("//input[@name ='password']"),"test@gmail.com");
    }
    @Given("Click on login button")
    public void click_on_login_button() {

       driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
    @Then("accountButton button is shown")
    public void account_button_is_shown() {
        Assert.assertTrue(
                isElementPresent(By.xpath("//button[@routerlink='/account']"))
        );
    }

    @When("enter invalid creds")
    public void enterInvalidCreds(DataTable table) {
        List<Map<String, String>> dataTable = table.asMaps();
        String email = dataTable.get(0).get("email");
        String password = dataTable.get(0).get("password");

        type(By.cssSelector("#defaultRegisterFormEmail"), email);
        type(By.xpath("//input[@name ='password']"), password);
    }

    @Then("error message is shown")
    public void errorMessageIsShown() {
        Assert.assertTrue(
                isElementPresent(By.cssSelector("#error-message"))
        );
    }

    @When("we enter {word} and {word} as invalid data")
    public void weEnterEmailAndPasswordAsInvalidData(String email, String password) {
        type(By.cssSelector("#defaultRegisterFormEmail"), email);
        type(By.xpath("//input[@name ='password']"), password);
    }

}
