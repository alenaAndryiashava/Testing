package pages.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TablePage {
    WebDriver driver;

    public TablePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public List<Map<String, String>> getTableData() {
        List<WebElement> data = new ArrayList<>();
        data = driver.findElements(By.xpath("//div[@role='rowgroup']"));
        List<Map<String, String>> rows = new ArrayList<>();

        for(int k = 0; k<=data.size()-1; k++) {
            List<String> cell = new ArrayList<>();
            Map<String, String> dataCell = new HashMap<>();
            String dataString = data.get(k).getText();
            if(!data.get(k).getText().contains("  ")) {
                for(int i=1; i<=6; i++) {
                    String locator = String.format("//div[@role=\"rowgroup\"][%s]//div[@class='rt-td'][%s]", k +1, i);
                    String cellData = driver.findElement(By.xpath(locator)).getText();
                    cell.add(cellData);
                }
                dataCell.put("First Name", cell.get(0));
                dataCell.put("Last Name", cell.get(1));
                dataCell.put("Age", cell.get(2));
                dataCell.put("Email", cell.get(3));
                dataCell.put("Salary", cell.get(4));
                dataCell.put("Department", cell.get(5));
                rows.add(dataCell);

            }
        }
//        System.out.println(rows);
        return rows;
    }
}
