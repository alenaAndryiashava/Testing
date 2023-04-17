package pages;

import com.codeborne.selenide.*;
import dto.Card;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.*;

public class HomePage {
    private static final SelenideElement secondaryHeader = $x("//div[@class='header_secondary_container']/span[@class='title']");
    private static final SelenideElement filter = $x("//select[@data-test='product_sort_container']");
    private static final SelenideElement basketButton = $x("//a[@class='shopping_cart_link']");
    private static final ElementsCollection cards = $$x("//div[@class='inventory_item']");
    private static final ElementsCollection socialLinks = $$x("//footer//ul/li/a");


    @Step("Проверяем переход на страницу")
    public HomePage checkLogin(){
        secondaryHeader.shouldHave(Condition.exactText("Products"));
        return this;
    }

    @Step("Проверяем наличие фильтра")
    public HomePage checkFilter(){
        filter.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверяем наличие корзины")
    public HomePage checkBasket(){
        basketButton.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверяем наличие счетчика в корзине")
    public HomePage checkBasketCounter(){
        basketButton.$x("./span[@class='shopping_cart_badge']").shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверяем наличие счетчика в корзине")
    public HomePage checkBasketCounter(int expectedCount){
        if(expectedCount == 0){
            basketButton.$x("./span[@class='shopping_cart_badge']").shouldNotBe(Condition.exist);
        } else {
            basketButton.$x("./span[@class='shopping_cart_badge']").shouldHave(Condition.exactText(String.valueOf(expectedCount)));

        }
        return this;
    }

    @Step("Проверяем наличие счетчика в корзине")
    public HomePage checkProducts(int size){
        cards.shouldHave(CollectionCondition.size(size));
        return this;
    }

    @Step("Получаем карточку по имени '{cardName}'")
    public Card saveCardData(String cardName){
        SelenideElement cardElement = cards.findBy(Condition.text(cardName));
        String description = cardElement.$x(".//div[@class='inventory_item_desc']").getText();
        String price = cardElement.$x(".//div[@class='inventory_item_price']").getText();
        String src = cardElement.$x(".//img").getAttribute("src");
        SelenideElement button = cardElement.$x(".//button");
        return new Card(cardName, description, price, src, button);
    }

    @Step("Проверяем карточку по имени '{cardName}'")
    public HomePage checkCardData(Card card){
        SelenideElement cardElement = cards.findBy(Condition.text(card.getName()));
        cardElement.$x(".//div[@class='inventory_item_desc']").shouldHave(Condition.exactText(card.getDescription()));
        cardElement.$x(".//div[@class='inventory_item_price']").shouldHave(Condition.exactText(card.getPrice()));
        cardElement.$x(".//img").shouldHave(Condition.attribute("src", card.getImgSrc()));
        return this;
    }

    public int getBasketCounter() {
        return basketButton.$x("./span[@class='shopping_cart_badge']").is(Condition.exist)
                ? Integer.parseInt(basketButton.$x("./span[@class='shopping_cart_badge']").getText())
                : 0;
    }

    @Step("Открываем корзину")
    public BasketPage openBasket(){
        basketButton.shouldBe(Condition.visible).click();
        return new BasketPage();
    }

    @Step("Проверяем ссылку")
    public HomePage checkLink(){
        for (int i = 0; i < socialLinks.size(); i++) {
            String social = socialLinks.get(i).getAttribute("href");
            socialLinks.get(i).click();
            switchTo().window(1);
            if(Objects.requireNonNull(social).contains("twitter.com")){
                Selenide.webdriver().shouldHave(WebDriverConditions.currentFrameUrlContaining("twitter.com"));
                closeWindow();
            } else if(Objects.requireNonNull(social).contains("facebook.com")){
                Selenide.webdriver().shouldHave(WebDriverConditions.currentFrameUrlContaining("facebook.com"));
                closeWindow();
            } else if(Objects.requireNonNull(social).contains("linkedin.com")){
                Selenide.webdriver().shouldHave(WebDriverConditions.currentFrameUrlContaining("linkedin.com"));
                closeWindow();
            }
            switchTo().window(0);
        }
        return this;
    }
}
