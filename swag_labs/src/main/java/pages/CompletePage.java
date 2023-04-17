package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dto.Card;
import io.qameta.allure.Step;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CompletePage {
    private final ElementsCollection cards = $$x("//div[@class='cart_item']");
    private final ElementsCollection cardsNames = $$x("//div[@class='cart_item']//div[@class='inventory_item_name']");
    private final SelenideElement taxLabel = $x("//div[@class='summary_tax_label']");
    private final SelenideElement totalSumLabel = $x("//div[@class='summary_info_label summary_total_label']");

    @Step("Получаем карточку по имени '{cardName}'")
    public Card saveCardData(String cardName){
        SelenideElement cardElement = cards.findBy(Condition.text(cardName));
        String description = cardElement.$x(".//div[@class='inventory_item_desc']").getText();
        String price = cardElement.$x(".//div[@class='inventory_item_price']").getText();
        return new Card(cardName, description, price);
    }

    @Step("Получаем числовое значение налога'")
    public Double getTax(){
        String taxDescription = taxLabel.getText();
        String tax = "";
        Pattern pattern = Pattern.compile("(\\d+.\\d+)");
        Matcher matcher = pattern.matcher(taxDescription);
        if(matcher.find()){
            tax = matcher.group(1);
        }
        return Double.parseDouble(tax);
    }

    @Step("Проверяем итоговую сумму")
    public CompletePage checkTotalSum(double sum){
        String totalSum = String.valueOf(sum);
        totalSumLabel.shouldHave(Condition.text(totalSum));
        return this;
    }

    @Step("Получаем имена всех карточек")
    public ArrayList<String> getAllCards(){
        return (ArrayList<String>) cardsNames.texts();
    }
}

