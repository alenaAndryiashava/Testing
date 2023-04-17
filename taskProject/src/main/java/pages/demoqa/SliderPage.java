package pages.demoqa;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SliderPage {
    WebDriver driver;

    public SliderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@type='range']")
    private WebElement slider;

    @FindBy(xpath = "//input[@id=\"sliderValue\"]")
    private WebElement sliderValue;

    public String getAttribute() {
        return slider.getAttribute("value");
    }

    public String getSliderValue() {
        return sliderValue.getAttribute("value");
    }

    public String getCss() {
        return sliderValue.getCssValue("range-slider");
    }

    public void moveSlider(String value) {
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, -500, 0).build().perform();
        String valueNow;

        do {
            actions.sendKeys(Keys.ARROW_RIGHT).perform();
            valueNow = getAttribute();
        } while (!valueNow.equals(value));
//        new WebDriverWait(driver, Duration.ofSeconds(20))
//                .until(ExpectedConditions.attributeToBe(slider,"value", value));

    }

    public void moveSliderWithFor(String value) {
        int val = Integer.parseInt(value);
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, -500, 0).build().perform();
        String valueNow;

        for(int i = 0; i<val; i++) {
            actions.sendKeys(Keys.ARROW_RIGHT).perform();
        }

    }
}
