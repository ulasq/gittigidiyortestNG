package base;

import java.sql.Connection;
import java.util.Collections;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;


/**
 * @author ulas.dikengul
 */
@Listeners(utilities.TestListener.class)
public class Base {

    public WebDriver driver;
    public WebDriverWait wait;
    public String myBrowser, environment;
    public Connection connection;


    /**
     * Her test baslamadan once calisir. Suite uzerinde belirtilen bilgiye gore
     * driver baslatilir.
     *
     * @param myBrowser   : Suite uzerinden belirtilen browser ismi. chrome veya
     *                    firefox
     * @param environment : Testin kosulacagi ortam
     */
    @SuppressWarnings("deprecation")
    @BeforeTest
    @Parameters({"myBrowser", "environment"})
    public void initDriver(String myBrowser, String environment) {

        if (System.getProperty("os.name").contains("Window")) {
            if (myBrowser.equalsIgnoreCase("chrome")) {

                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");

                DesiredCapabilities capability = new DesiredCapabilities();
                capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);

                ChromeOptions options = new ChromeOptions();

                options.addArguments("--start-maximized");
                options.setExperimentalOption("useAutomationExtension", false);
                options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
                options.addArguments("--allow-running-insecure-content");
                options.addArguments("ignore-certificate-errors");
                options.addArguments("window-size=1920,1080");
                options.addArguments("no-sandbox");
                capability.setCapability(ChromeOptions.CAPABILITY, options);

                driver = new ChromeDriver(capability);
                wait = new WebDriverWait(driver, 90);
                driver.manage().window().maximize();

            } else if (myBrowser.equalsIgnoreCase("firefox")) {
                /*
                 * FireFox driver icin init islemleri burada olacak
                 */
            }
        }
    }

    /**
     * Her test basinda ortak kullanilan methodlarin oldugu classsi baslatir
     *
     * @param environment : ortam bilgisi
     * @param username    : login olan kullanici ismi
     * @param password    : login olan kullanici sifresi
     */
    @BeforeTest
    @Parameters({"environment", "username", "password"})
    public void initGenericMethods(String environment, String username, String password) {
        this.environment = environment;
    }

    /**
     * Her test sonunda calisir. Yaratilan driver'i sonlandirir.
     */
    @AfterTest
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
