package com.telran.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends PageBase{

    public void login(String email, String password) {
        $("#defaultRegisterFormEmail").click();
        $("#defaultRegisterFormEmail").val(email);

        $(by("placeholder", "Password")).click();
        $(by("placeholder", "Password")).sendKeys(password);

        $(byXpath("//button[@type='submit']")).click();
        //$(byCssSelector("#login-form > div:nth-child(3) > div.col-sm-5 > button")).click();
    }

    public void validateLogin() {
        $(byXpath("//button[@routerlink='/account']")).shouldBe(Condition.visible);
    }
}
