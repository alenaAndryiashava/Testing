package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.demoqa.TablePage;

import java.util.List;
import java.util.Map;

public class TableTest extends TestBase{
    String url = "https://demoqa.com/webtables";
    TablePage tablePage;
    List<Map<String, String>> rows;

    @BeforeMethod
    public void openUrl() {
        driver.get(url);
    }

    @Test
    public void tableTest() {
        tablePage = new TablePage(driver);
        rows = tablePage.getTableData();
        String email = rows.get(2).get("Email");
        System.out.println(email + "123456");

    }
}
