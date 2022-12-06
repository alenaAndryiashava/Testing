import org.testng.annotations.Test;

public class TestExcelData extends TestBase {
    @Test(dataProvider = "excelWrongDataRead", dataProviderClass = DataProviders.class)
    public void BadAuthTestWithDataProviderExcel(String email, String password) {
        logger.info("Starting method: bad_AuthTest");
        logger.info("try to auth with bad datasets");
        badAuth(email, password);
        sleepMethod();
        logger.info("Test passed");
    }

    @Test(dataProvider = "csvWrongDataRead", dataProviderClass = DataProviders.class)
    public void BadAuthTestWithDataProviderCSV(String email, String password) {
        logger.info("Starting method: bad_AuthTest");
        logger.info("try to auth with bad datasets");
        badAuth(email, password);
        sleepMethod();
        logger.info("Test passed");
    }

    @Test(dataProvider = "excelCorrectDataRead", dataProviderClass = DataProviders.class)
    public void CorrectAuthTestWithDataProviderExcel(String email, String password) {
        logger.info("Starting method: bad_AuthTest");
        logger.info("try to auth with bad datasets");
        goodAuth(email, password);
        sleepMethod();
        logger.info("Test passed");
    }
}


