import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;
import org.testng.Assert;


import java.awt.*;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import org.monte.screenrecorder.ScreenRecorder;
import org.monte.media.Format;
import org.monte.media.FormatKeys;
import org.monte.media.math.Rational;
import static org.monte.media.FormatKeys.*;
import static org.monte.media.VideoFormatKeys.*;


import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestBase extends DataProviders{
    final static Logger logger = LoggerFactory.getLogger(TestBase.class);
    WebDriver driver ;
    String browser;
    private ScreenRecorder screen;
    String recordsFolder= "src/test/resources/records";

    @BeforeSuite(alwaysRun=true)
    public void Prepare(){
        deleteAllRecordings();

        logger.info("Running a test: prepare in BeforeSuite, initializing WebDriver, maximizing window and opening login page ");
        String path;

        browser = System.getProperty("browser");
        logger.info("Running test system property browser set to " + browser);

        if (browser.equals(Browser.CHROME.browserName())) {
            path = System.getenv("chromeDriver");
            System.setProperty("webdriver.chrome.driver", path);
            driver = new ChromeDriver();
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            path = System.getenv("firefoxDriver");
            System.setProperty("webdriver.gecko.driver", path);
            driver = new FirefoxDriver();
        }  else if (browser.equals(Browser.OPERA.browserName())) {
            path = System.getenv("operaDriver");
            System.setProperty("webdriver.chrome.driver", path);
            driver = new ChromeDriver();
        } else if (browser.equals(Browser.EDGE.browserName())) {
            path = System.getenv("safariDriver");
            System.setProperty("webdriver.safari.driver", path);
            driver = new SafariDriver();
        }  else {
            logger.error("No supported browser specified. Supported browsers: chrome, firefox,edge, opera");
        }

        driver.get("https://derrick686.softr.app/login");
        driver.manage().window().maximize();
    }

    @DataProvider
    public Iterator<Object[]> getWrongLoginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"email","password"});
        list.add(new Object[]{"billyeexample.com","123456"});
        list.add(new Object[]{"","123456"});
        list.add(new Object[]{"billye@example.com",""});
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> getPartialLinkText(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"PROJECT OVERVIEW"});
        list.add(new Object[]{"CLIENTS"});
        list.add(new Object[]{"TEAM"});
        list.add(new Object[]{"INVOICES"});
        return list.iterator();
    }

    public void enterEmail(String emailEntered){
        WebElement email = driver.findElement(By.cssSelector("#sw-form-capture-email-input"));
        email.click();
        email.clear();
        email.sendKeys(emailEntered);
        sleepMethod();

    }
    public void enterPassword(String passwordEntered){
        WebElement password = driver.findElement(By.cssSelector("#sw-form-password-input"));
        password.click();
        password.clear();
        password.sendKeys(passwordEntered);
        sleepMethod();
    }

    public void submitButtonClick(){
        //click submit button
        sleepMethod();
        WebElement button = driver.findElement(By.cssSelector("#sw-sign-in-submit-btn"));
        button.click();

    }
    public void checkErrorMessage(){
        //verification error message
        sleepMethod();
        String text = driver.getPageSource();
        System.out.println(text.contains("Invalid email or password"));
        Assert.assertEquals(text.contains("Invalid email or password"),Boolean.TRUE);

    }
    public void badAuth(String email, String password){
        sleepMethod();
        enterEmail(email);
        enterPassword(password);
        submitButtonClick();
        sleepMethod();
        checkErrorMessage();
        sleepMethod();
    }
    public void auth(String email, String password){
        sleepMethod();
        enterEmail(email);
        enterPassword(password);
        submitButtonClick();
        sleepMethod();

    }

    public void goodAuth(String email, String password){
        sleepMethod();
        enterEmail(email);
        enterPassword(password);
        submitButtonClick();
        sleepMethod();
        checkStartPage();
        logout();
        openLoginPage();
        sleepMethod();
    }
    public void managerAuth() {
        auth("billye@example.com","123456");
    }
    public void clientAuth(){
        auth("lucie@example.com", "123456");
    }
    public void consultantAuth(){
        auth("edra@example.com", "123456");
    }

    public void checkStartPage(){
        sleepMethod();
        Assert.assertEquals(searchInPageSource("Welcome to your Client Portal"),Boolean.TRUE);
    }

    public void sleepMethod(){
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            System.out.println("InterruptedException");
        }
    }
    public String takeScreenshot() {
        File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("src/test/resources/screenshots/screen"+System.currentTimeMillis()+".png");
        try {
            screenshot.createNewFile();
            Files.copy(tmp,screenshot);
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            return "Failed to create a screenshot";
        }
        return screenshot.getAbsolutePath();
    }
    public void startRecording() {
        File file = new File(recordsFolder);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle captureSize = new Rectangle(0,0, dimension.width, dimension.height);
        GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
        logger.info("Starting screen recording");
        try {
            screen = new Recorder(gc, captureSize,
                    new Format(MediaTypeKey, FormatKeys.MediaType.FILE, MimeTypeKey, MIME_AVI),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_MJPG, CompressorNameKey, ENCODING_AVI_MJPG,
                            DepthKey, 24, FrameRateKey, Rational.valueOf(15), QualityKey, 1.0f, KeyFrameIntervalKey, 15 * 60),
                    new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black", FrameRateKey, Rational.valueOf(30)),
                    null, file, "MyVideo");
            screen.start();
        } catch (IOException | AWTException e1){
            logger.error(e1.getMessage());
        }
    }

    public void stopRecoding() {
        logger.info("Stopping screen recording");
        try {
            screen.stop();
        } catch (IOException e1){
            logger.error(e1.getMessage());
        }
    }

    public void deleteAllRecordings(){
        File dir = new File(recordsFolder);
        for (File f: dir.listFiles()){
            try
            {
                f.delete();
            }
            catch (Exception e)
            {
                logger.error("Error while cleaning Records folder " + e.getMessage());
            }
        }
        logger.info("Cleaned Records folder");
    }


    public void logout(){
        // log out of the manager account if we are currently
        driver.findElement(By.id("navbarDropdown")).click();
        driver.findElement(By.cssSelector(".d-item.d-flex.justify-content-start.align-items-center.navigate")).click();
    }
    public void openLoginPage(){
        driver.get("https://derrick686.softr.app/login");
    }
    public boolean searchInPageSource(String text){
        return driver.getPageSource().contains(text);

    }
    public void searchByLinkText(String text){
        driver.findElement(By.partialLinkText(text));
    }
    public void searchByCssSelector(String text,String word){
        sleepMethod();
        WebElement webElement =  driver.findElement(By.cssSelector(text));
        webElement.sendKeys(word);
        sleepMethod();
    }
    public void searchClientsBy(String stringS) {
        WebElement input = driver.findElement(By.xpath("//*[@id=\"list2\"]/div[1]/div/div/div/input"));
        input.click();
        input.clear();
        input.sendKeys(stringS);
        input.sendKeys(Keys.ENTER);
        sleepMethod();
    }

    @AfterTest
    public void exit() {
        logger.info("Leaving the site. Browser is closing.");
        driver.quit();
    }
}

