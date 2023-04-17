package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.demoqa.TextBoxPage;

public class TextBoxTest extends TestBase{
    Faker faker = new Faker();
    String name = faker.name().firstName();
    String email = faker.internet().emailAddress();
    String currentAddress = faker.address().fullAddress();
    String permanentAddress = faker.address().fullAddress();

    String url = "https://demoqa.com/text-box";
    TextBoxPage textBoxPage;

    @BeforeMethod
    public void precondition() {
        driver.get(url);
    }

    @Test
    public void boxTest() {
        textBoxPage = new TextBoxPage(driver);
        textBoxPage.fillTextBox(name,email, currentAddress, permanentAddress);
        textBoxPage.waitText();

//        Assert.assertEquals(textBox.getName(), "Name:" + name);

        Assert.assertEquals(textBoxPage.getTextFromInfoField("name"), "Name:" + name);
        Assert.assertEquals(textBoxPage.getTextFromInfoField("email"), "Email:" + email);
        Assert.assertEquals(textBoxPage.getTextFromInfoField("currentAddress"),
                "Current Address :" + currentAddress);
        Assert.assertEquals(textBoxPage.getTextFromInfoField("permanentAddress"),
                "Permananet Address :" + permanentAddress);
    }
}
