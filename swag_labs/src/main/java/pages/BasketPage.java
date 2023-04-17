package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.*;

public class BasketPage {
    private ElementsCollection buttons = $$x("//div[@class='cart_item']//button");
    private SelenideElement checkoutButton = $x("//button[@id='checkout']");

    @Step("Удаляем товар из корзины")
    public BasketPage deleteProduct(){
        buttons.get(0).click();
        return this;
    }

    @Step("Переходим на страницу оплаты")
    public OverviewPage checkout(){
        checkoutButton.click();
        return new OverviewPage();
    }
}
