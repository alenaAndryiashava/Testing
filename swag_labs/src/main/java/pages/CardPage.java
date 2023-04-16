package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import dto.Card;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class CardPage {
    private final SelenideElement img = $x("//div[@class='inventory_details']//img");
    private final SelenideElement name = $x("//div[@class='inventory_details_desc_container']//div[contains(@class, 'inventory_details_name')]");
    private final SelenideElement description = $x("//div[@class='inventory_details_desc_container']//div[contains(@class, 'inventory_details_desc')]");
    private final SelenideElement price = $x("//div[@class='inventory_details_desc_container']//div[contains(@class, 'inventory_details_price')]");
    private final SelenideElement button = $x("//div[@class='inventory_details']//button");

    @Step("Проверяем данные товара")
    public CardPage checkCardData(Card card){
        img.shouldHave(Condition.attribute("src",card.getImgSrc()));
        name.shouldHave(Condition.exactText(card.getName()));
        description.shouldHave(Condition.exactText(description.getText()));
        price.shouldHave(Condition.exactText(price.getText()));
        button.shouldBe(Condition.visible);
        return this;
    }
}
