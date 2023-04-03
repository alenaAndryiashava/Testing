package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private static final SelenideElement secondaryHeader = $x("//div[@class='header_secondary_container']/span[@class='title']");
    private static final SelenideElement filter = $x("//select[@data-test='product_sort_container']");
    private static final SelenideElement basketButton = $x("//a[@class='shopping_cart_link']");
    private static final ElementsCollection cards = $$x("//div[@class='inventory_item']");

    @Step("Проверяем переход на страницу")
    public HomePage checkLogin() {
        secondaryHeader.shouldHave(Condition.exactText("Products"));
        return this;
    }

    @Step("Проверяем наличие фильтра")
    public HomePage checkFilter() {
        filter.shouldHave(Condition.visible);
        return this;
    }
    @Step("Проверяем наличие корзины")
    public HomePage checkBasket() {
        basketButton.shouldHave(Condition.visible);
        return this;
    }
    @Step("Проверяем наличие счетчика товаров в корзине")
    public HomePage checkBasketCounter() {
        basketButton.$x("./span[@class='shopping_cart_badge'").shouldHave(Condition.visible);
        return this;
    }
    @Step("Проверяем наличие карточек с товарами")
    public HomePage checkProducts(int size) {
        cards.shouldHave(CollectionCondition.size(size));
        return this;
    }
    @Step("Получаем карточку по имени '{cardName}'")
    public HomePage checkCardName(Card card) {

        /*Получение случайного элемента списка
        SelenideElement randomCard = cards.get(new Random().nextInt(cards.size()));

         */

        SelenideElement cardElement = cards.findBy(Condition.text(card.getName()));
        String description = cardElement.$x(".//div[@class='inventory_item_desc']").getText();
        String price = cardElement.$x(".//div[@class='inventory_item_price']").getText();

        card.setDescription(description);
        card.setPrice(price);
        return this;
    }
}
