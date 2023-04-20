package pages.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DatePickerPage {
    WebDriver driver;

    public DatePickerPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@id=\"datePickerMonthYearInput\"]")
    private WebElement datePickerField;

    @FindBy(xpath = "//div[@class=\"react-datepicker__month-container\"]")
    private WebElement calendar;
    @FindBy(xpath = "//button[@aria-label=\"Next Month\"]")
    private WebElement nextMonth;
    @FindBy(xpath = "//input[@id=\"dateAndTimePickerInput\"]")
    private WebElement dateTimePickerField;
    @FindBy(xpath = "//div[@class=\"react-datepicker\"]")
    private WebElement dateTimeCalendar;

    public boolean getCalendar(){
        datePickerField.click();
        return calendar.isDisplayed();
    }

    public void chooseDate(String date){
        driver.findElement(By.xpath(String.format("//div[@aria-label='Choose %s']", date))).click();
    }

    public void chooseTime(String time){
        driver.findElement(By.xpath(String.format("//li[normalize-space()='%s']", time))).click();
    }
    public String getDateFromField(){
        return datePickerField.getAttribute("value");
    }

    public void getNextMonth(){
        nextMonth.click();
    }

    public boolean getDateTimeCalendar(){
        dateTimeCalendar.click();
        return dateTimeCalendar.isDisplayed();
    }

    public String getDateTimeFromField(){
        return dateTimePickerField.getAttribute("value");
    }
}
