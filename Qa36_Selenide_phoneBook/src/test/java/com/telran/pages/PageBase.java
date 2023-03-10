package com.telran.pages;

import static com.codeborne.selenide.Selenide.open;

public class PageBase {
    String baseURL = "http://phonebook.telran-edu.de:8080/user/login";

    public PageBase(){
        open(baseURL);
    }
}
