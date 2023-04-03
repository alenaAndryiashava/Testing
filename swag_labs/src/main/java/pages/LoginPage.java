package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {
    private static final SelenideElement loginLogo = $x("//div[@class='login_logo']");
    private static final SelenideElement loginInput = $x("//input[@id='user-name']");
    private static final SelenideElement passwordInput = $x("//input[@id='password']");
    private static final SelenideElement loginButton = $x("//input[@id='login-button']");
    private static final SelenideElement errorContainer = $x("//div[contains(@class,'error-message-container')]");


    @Step("Вводим пароль '{login}'")
    public LoginPage setLogin(String login) {
        loginInput.shouldBe(Condition.visible).sendKeys(login);
        return this;
    }

    @Step("Вводим пароль '{password}'")
    public LoginPage setPassword(String password) {
        passwordInput.shouldBe(Condition.visible).sendKeys(password);
        return this;
    }

    @Step("Кликаем кнопку Login")
    public HomePage login() {
        loginButton.shouldBe(Condition.visible).click();
        return new HomePage();
    }

    @Step("Проверяем название ресурса '{resourceName}' ")
    public LoginPage checkResourceName(String resourceName) {
        loginLogo.shouldHave(Condition.exactText(resourceName));
        return this;
    }
    @Step("Проверяем наличие ошибки")
    public LoginPage checkError() {
        errorContainer.shouldHave(Condition.cssClass("error"));
        return this;
    }

    @Step("Закрываем ошибку")
    public LoginPage closeError() {
        errorContainer.$x(".//button").click();
        errorContainer.shouldNotHave(Condition.cssClass("error"));
        return this;
    }
}







