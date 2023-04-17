package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class OverviewPage {
    private final SelenideElement firstNameInput = $x("//input[@id='first-name']");
    private final SelenideElement lastNameInput = $x("//input[@id='last-name']");
    private final SelenideElement postalCodeInput = $x("//input[@id='postal-code']");
    private final SelenideElement continueButton = $x("//input[@id='continue']");

    @Step("Вводим имя")
    public OverviewPage setName(String name){
        firstNameInput.sendKeys(name);
        return this;
    }

    @Step("Вводим фамилию")
    public OverviewPage setLastName(String lastName){
        lastNameInput.sendKeys(lastName);
        return this;
    }

    @Step("Вводим код")
    public OverviewPage setPostalCode(String postalCode){
        postalCodeInput.sendKeys(postalCode);
        return this;
    }

    @Step("Вводим код")
    public CompletePage continueOrder(){
        continueButton.click();
        return new CompletePage();
    }
}
