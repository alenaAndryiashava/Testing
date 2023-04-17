package dto;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.CardPage;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;

public class Card {
    private String name;
    private String description;
    private String price;
    private String imgSrc;
    private SelenideElement button;

    public Card(String name, String description, String price, String imgSrc, SelenideElement button) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgSrc = imgSrc;
        this.button = button;
    }
    public Card(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Step("Проверка добавления товара в корзину")
    public Card addToBasket() {
        this.button.shouldBe(Condition.visible);
        if(Objects.requireNonNull(this.button.getAttribute("id")).contains("add-to-cart")){
            this.button.click();
        }
        return this;
    }

    @Step("Проверка удаления товара из корзины")
    public Card removeFromBasket(){
        /*if (this.button.has(Condition.id("remove-sauce-labs-backpack"))) {
            this.button.click();

         */
        this.button.shouldBe(Condition.visible);
        if(Objects.requireNonNull(this.button.getAttribute("id")).contains("remove")){
            this.button.click();
        }
        return this;
    }
    @Step("Открываем страницу товара")
    public CardPage openCardPage(){
        $x("//div[.='" + this.name + "']").click();
        return new CardPage();
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
