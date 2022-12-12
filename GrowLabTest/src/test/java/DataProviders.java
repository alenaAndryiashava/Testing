import com.github.hemanthsridhar.CSVUtils;
import com.github.hemanthsridhar.ExcelUtils;
import com.github.hemanthsridhar.lib.ExtUtils;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class DataProviders {
    @DataProvider
    public static Iterator<Object[]> wrongDataAuthFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/wrong_creds.csv")));
        String line = reader.readLine();

        while (line != null) {
            String[] split = line.split(",");
            list.add(split);
            line = reader.readLine();
        }

        return list.iterator();
    }
    @DataProvider
    public static Iterator<Object[]> positiveDataAuthFromCSV() throws IOException {
        List<Object[]> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/right_creds.csv")));
        String line = reader.readLine();

        while (line != null) {
            String[] split = line.split(",");
            list.add(split);
            line = reader.readLine();
        }


        return list.iterator();
    }

    @DataProvider
    public Object[][] csvWrongDataRead() throws Exception {
        String path = "src/test/resources/wrong_creds.csv";
        ExtUtils ext = new CSVUtils(path, true);
        return ext.parseData();
    }

    //    Документация - https://github.com/hemanthsridhar/testng-excel-dataprovider
    @DataProvider
    public Object[][] excelWrongDataRead() throws Exception {
        ExtUtils ext = new ExcelUtils("src/test/resources/excelData.xlsx", "wrongData");
        return ext.parseData();
    }

    @DataProvider
    public Object[][] excelCorrectDataRead() throws Exception {
        ExtUtils ext = new ExcelUtils("src/test/resources/excelData.xlsx", "correctData");
        return ext.parseData();
    }



}





