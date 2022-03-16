package utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

/**
 * @author ulas.dikengul
 */
public class TestListener implements ITestListener {
    private UtilityMethods utility = new UtilityMethods();
    Logger log = LogManager.getLogger(TestListener.class);


    public void onStart(ITestContext context) {
    }

    public void onFinish(ITestContext context) {
    }

    public void onTestStart(ITestResult result) {
        Reporter.log(result.getName() + " isimli Test Baslatiliyor");
        log.info(result.getName() + " isimli Test Baslatiliyor");

    }

    public void onTestSuccess(ITestResult result) {
        Reporter.log(result.getName() + " isimli Test Basarili");
        log.info(result.getName() + " isimli Test Basarili");
    }

    public void onTestFailure(ITestResult result) {
        utility.captureScreenshot("screenshot");
        Reporter.log(result.getName() + " isimli Test Basarisiz");
        log.info(result.getName() + " isimli Test Basarisiz");
    }

    public void onTestSkipped(ITestResult result) {
        utility.captureScreenshot("screenshot");
        Reporter.log(result.getName() + " isimli Test Atlandi");
        log.info(result.getName() + " isimli Test Atlandi");
    }

}
