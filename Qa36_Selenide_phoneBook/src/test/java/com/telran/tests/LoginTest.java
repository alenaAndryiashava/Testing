package com.telran.tests;

import com.telran.pages.LoginPage;
import org.junit.Before;
import org.junit.Test;


public class LoginTest {
    LoginPage loginPage;

   @Before
    public void setUp(){
        loginPage = new LoginPage();
    }

    @Test
    public void loginPositiveTest(){
        loginPage.login("test@gmail.com", "test@gmail.com");
        loginPage.validateLogin();
    }
}
